package ch.boxi.javaUtil.observableCollection;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("unchecked")
public class ObservableHashSet<E> extends ObservabelCollection<E> implements Set<E> {

	public ObservableHashSet() {
		super(new HashSet<E>());
	}

	public <E> E[] toArray(E[] a) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

}
