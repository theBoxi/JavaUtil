package ch.boxi.javaUtil.observableCollection;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

@SuppressWarnings("unchecked")
public class ObservableTreeSet<E> extends ObservabelCollection<E> implements SortedSet<E> {
	
	SortedSet<E> mySet = null;
	
	public ObservableTreeSet() {
		super(new TreeSet<E>());
		mySet = (TreeSet<E>) myCol;
	}
	
	public ObservableTreeSet(Comparator<E> comperator) {
		super(new TreeSet<E>(comperator));
		mySet = (TreeSet<E>) myCol;
	}

	public Comparator<? super E> comparator() {
		return mySet.comparator();
	}

	public E first() {
		return mySet.first();
	}

	public SortedSet<E> headSet(E arg0) {
		return mySet.headSet(arg0);
	}

	public E last() {
		return mySet.last();
	}

	public SortedSet<E> subSet(E arg0, E arg1) {
		return mySet.subSet(arg0, arg1);
	}

	public SortedSet<E> tailSet(E arg0) {
		return mySet.tailSet(arg0);
	}

	public <E> E[] toArray(E[] a) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
