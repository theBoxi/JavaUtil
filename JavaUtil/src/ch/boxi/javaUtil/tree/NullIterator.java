package ch.boxi.javaUtil.tree;

import java.util.Iterator;

class NullIterator<T> implements Iterator<T> {

	@Override
	public boolean hasNext() {
		return false;
	}

	@Override
	public T next() {
		throw new NullPointerException();
	}

	@Override
	public void remove() {
		throw new NullPointerException();
	}

}
