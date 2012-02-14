package ch.boxi.javaUtil.tree;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

class BreadthFirstIterator<T extends TreeNode<T> > implements Iterator<T> {
	private Iterator<T> iterationLayer;
	private LinkedList<T> nextLayer;
	
	public BreadthFirstIterator(T rootNode) {
		Collection<T> itrLayerCol = new LinkedList<T>();
		itrLayerCol.add(rootNode);
		iterationLayer = itrLayerCol.iterator();
		nextLayer = new LinkedList<T>();
	}
	
	@Override
	public boolean hasNext() {
		if(iterationLayer.hasNext()){
			return true;
		} else{
			return !nextLayer.isEmpty();
		}
	}

	@Override
	public T next() {
		if(iterationLayer.hasNext()){
			T objToReturn =  iterationLayer.next();
			nextLayer.addAll(objToReturn.getChilds());
			return objToReturn;
		} else{
			if(nextLayer.size() > 0){
				iterationLayer = nextLayer.iterator();
				nextLayer = new LinkedList<T>();
				return next();
			}else{
				throw new NoSuchElementException();
			}
		}
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

}
