package ch.boxi.javaUtil.observableCollection;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;

public class ObservabelCollection<E> extends Observable implements Collection<E> {

	protected Collection<E> myCol = null;
	
	public ObservabelCollection() {
		super();
		myCol = new LinkedList<E>();
	}
	
	protected ObservabelCollection(Collection<E> innerCollection){
		super();
		myCol = innerCollection;
		myCol.clear();
	}

	public boolean add(E arg0) {
		if(myCol.add(arg0)){
			notifyObservers(Change.ADD, arg0);
			return true;
		}
		return false;
	}

	public boolean addAll(Collection<? extends E> arg0) {
		if(myCol.addAll(arg0)){
			for(E arg: arg0){
				notifyObservers(Change.ADD, arg);
			}
			return true;
		}
		return false;
	}

	public void clear() {
		for(E obj: myCol){
			notifyObservers(Change.REMOVE, obj);
		}
	}

	public boolean contains(Object arg0) {
		return myCol.contains(arg0);
	}

	public boolean containsAll(Collection<?> arg0) {
		return myCol.containsAll(arg0);
	}

	public boolean isEmpty() {
		return myCol.isEmpty();
	}

	public Iterator<E> iterator() {
		return myCol.iterator();
	}

	public boolean remove(Object arg0) {
		if(myCol.remove(arg0)){
			notifyObservers(Change.REMOVE, arg0);
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public boolean removeAll(Collection<?> arg0) throws ClassCastException{
		boolean changed = false;
		for(Object o: arg0){
			E e = (E) o;
			changed |= remove(e);
		}
		return changed;
	}

	public boolean retainAll(Collection<?> arg0){
		boolean changed = false;
		for(E e: myCol){
			if( !arg0.contains(e))
				changed |= remove(e);
		}
		return changed;
	}

	public int size() {
		return myCol.size();
	}
	
	protected void notifyObservers(Change c, Object o){
		setChanged();
		notifyObservers(new NotifyMsg(c, o));
	}

	public Object[] toArray() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public <E> E[] toArray(E[] a) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

}
