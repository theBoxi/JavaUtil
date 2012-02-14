package ch.boxi.javaUtil.id;

import java.io.Serializable;


public abstract class BaseID implements Comparable<BaseID>, Serializable{
	private static final long serialVersionUID = 333922354029022637L;

	protected final Long dbRepresentiv;
	
	public BaseID(long dbRepresentive){
		this.dbRepresentiv = dbRepresentive;
	}
	
	public long getLongValue(){
		return dbRepresentiv;
	}
	
	@Override
	public String toString(){
		return Long.toString(dbRepresentiv);
	}

	@Override
	public int compareTo(BaseID o) {
		return dbRepresentiv.compareTo(o.dbRepresentiv);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dbRepresentiv == null) ? 0 : dbRepresentiv.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseID other = (BaseID) obj;
		if (dbRepresentiv == null) {
			if (other.dbRepresentiv != null)
				return false;
		} else if (!dbRepresentiv.equals(other.dbRepresentiv))
			return false;
		return true;
	}
}
