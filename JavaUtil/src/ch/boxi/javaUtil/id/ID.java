package ch.boxi.javaUtil.id;

import java.io.Serializable;

public interface ID extends Serializable, Comparable<ID>{
	public long getLongValue();
}