package ch.boxi.javaUtil.tree;

import java.util.Iterator;
import java.util.NoSuchElementException;

//Breadth First Search
class DepthFirstIterator<T extends TreeNode<T> > implements Iterator<T> {
	private T rootNode;
	private TraversationalOrder order;
	private boolean returndThis = false;
	private Iterator<T> childIterator;
	private Iterator<T> childRootIterator;
	
	DepthFirstIterator(T rootNode, TraversationalOrder order){
		this.order = order;
		this.rootNode = rootNode;
		childIterator = rootNode.getChilds().iterator();
		childRootIterator = new NullIterator<T>();
	}	
	
	/**
	 * Calls constructor with TraversationalOrder.PreOrder 
	 * @param rootNode
	 */
	DepthFirstIterator(T rootNode){
		this(rootNode, TraversationalOrder.PreOrder);
	}
	
	@Override
	public boolean hasNext() {
		if(rootNode == null){
			throw new NullPointerException("Root node not inizialised");
		}
		
		if(order.equals(TraversationalOrder.PreOrder)){
			if(!returndThis){
				return true;
			}
		}
		
		if(childRootIterator.hasNext()){
			return true;
		}else{
			if(childIterator.hasNext()){
				childRootIterator = new DepthFirstIterator<T>(childIterator.next(), order);
				return childRootIterator.hasNext();
			}else{
				if(order.equals(TraversationalOrder.PostOrder)){
					if(!returndThis){
						return true;
					}
				}
				return false;
			}	
		}
	}

	@Override
	public T next() {
		if(order.equals(TraversationalOrder.PreOrder)){
			if(!returndThis){
				returndThis = true;
				return rootNode;
			}
		}
		
		if(childRootIterator.hasNext()){
			return childRootIterator.next();
		}else{
			if(childIterator.hasNext()){
				childRootIterator = new DepthFirstIterator<T>(childIterator.next(), order);
				return childRootIterator.next(); // Has at least his own rootNode
			}else{
				if(order.equals(TraversationalOrder.PostOrder)){
					if(!returndThis){
						returndThis = true;
						return rootNode;
					}
				}
				throw new NoSuchElementException();
			}	
		}
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

}
