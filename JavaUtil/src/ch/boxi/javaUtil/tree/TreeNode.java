package ch.boxi.javaUtil.tree;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;import java.util.Observable;
import java.util.Observer;
import java.util.Stack;
import java.io.IOException;

import javax.swing.tree.TreePath;

public abstract class TreeNode<T extends TreeNode<T>> extends Observable implements Iterable<T>, Observer  {	
	private T parent;
	private Collection<T> children;
	private int subTreeSize;
	
	public TreeNode(T parent){
		this();
		if(parent != null){
			parent.structureAddChild(getThis());
		}
	}
	
	public TreeNode(){
		subTreeSize = 1;
		children = new LinkedList<T>();
	}
	
	public T getParent(){
		return parent;
	}
	
	protected boolean structureAddChild(T child){
		boolean ok = false;
		try{
			child.structureSetParent(getThis());
			ok = children.add(child);
			if(ok){
				child.structureSetParent(getThis());
				notifyChildChangedSize(child.size());
				child.addObserver(this);
				changed();
			}
		}catch(IOException e){
			ok = false;
		}		
		return ok;
	}
	
	protected boolean structureRemoveChild(T child){
		boolean ok = false;
		try{
			ok = children.remove(child);
			if(ok){
				child.structureSetParent(null);
				notifyChildChangedSize(-1 * child.size());
				child.deleteObserver(this);
				changed();
			}
		}catch(IOException e){
			ok = false;
		}
		return ok;
	}
	
	public Collection<T> getChilds(){
		return Collections.unmodifiableCollection(children);
	}
	
	protected void notifyChildChangedSize(int differents){
		subTreeSize += differents;
		if(!isRootNode()){
			parent.notifyChildChangedSize(differents);
		}
	}
	
	protected void structureSetParent(T parent) throws IOException{
		this.parent = parent;
		changed();
	}
	
	public TreePath getPath(){
		Stack<T> path = new Stack<T>();
		T pointer = getThis();
		path.add(pointer);
		while(pointer.getParent() != null){
			pointer = pointer.getParent();
			path.add(pointer);
		}
		Object[] retPath = new Object[path.size()];
		
		int i = 0;
		for(T element: path){
			retPath[i] = element;
			i++;
		}
		
		return new TreePath(retPath);
	}
	
	public Iterator<T> iterator(){
		return new DepthFirstIterator<T>(getThis());
	}
	
	public Iterator<T> iteratorDepthFirtst(TraversationalOrder order){
		return new DepthFirstIterator<T>(getThis(), order);
	}
	
	public Iterator<T> iteratorBreadthFirtst(){
		return new BreadthFirstIterator<T>(getThis());
	}
	
	public boolean isRootNode(){
		return parent == null;
	}
	
	public int size(){
		return subTreeSize;
	}
	
	public boolean isLeaf(){
		return children.size() == 0;
	}
	
	public void update(Observable o, Object arg){
		changed(arg);
	}
	
	protected void changed(Object arg){
		setChanged();
		notifyObservers(arg);
	}
	
	protected void changed(){
		changed(null);
	}
	
	protected abstract T getThis();
}


