package ch.boxi.javaUtil.tree;

import java.util.Observable;
import java.util.Observer;

import javax.swing.tree.TreePath;

import ch.boxi.javaUtil.helper.ObserverHelper;
import ch.boxi.javaUtil.tree.helper.AbstractTreeModel;

public class TreeNodeModel<T extends TreeNode<T>> extends AbstractTreeModel implements Observer{
	
	private T rootNode;
	
	public TreeNodeModel(){
		this(null);
	}
	
	public TreeNodeModel(T rootNode){
		setRooteNode(rootNode);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getChild(Object parent, int index) {
		T myParent = (T) parent;
		int count = 0;
		T retChild = null;
		for(T child: myParent.getChilds()){
			if(index == count){
				retChild = child;
				break;
			}
			count++;
		}
		return retChild;
	}
	
	public void setRooteNode(T newRoot){
		ObserverHelper.unregisterObserverIfPossible(this, rootNode);
		rootNode = newRoot;
		ObserverHelper.registerObserverIfPossible(this, rootNode);
		fireStructureChanged();
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getChildCount(Object parent) {
		T myParent = (T) parent;
		return myParent.getChilds().size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getIndexOfChild(Object parent, Object child) {
		T myParent = (T) parent;
		int count = 0;
		for(T myChild: myParent.getChilds()){
			if(child.equals(myChild)){
				break;
			}
			count++;
		}
		return count;
	}

	@Override
	public Object getRoot() {
		return rootNode;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean isLeaf(Object node) {
		T myNode = (T) node;
		return myNode.isLeaf();
	}

	@Override
	public void valueForPathChanged(TreePath arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		fireStructureChanged();
		setChanged();
		notifyObservers();
	}
}
