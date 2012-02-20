package ch.boxi.javaUtil.id;

import org.apache.commons.lang3.StringUtils;

public abstract class PrefixedID extends BaseID{
	private static final long serialVersionUID = 8187877066413541261L;
	
	protected final String prefix;
	
	public PrefixedID(String prefix, long dbRepresentive) {
		super(dbRepresentive);
		this.prefix = prefix;
	}
	
	public String getPrefix() {
		return prefix;
	}
	
	@Override
	public String toString(){
		String toString = "";
		if(StringUtils.isNotEmpty(prefix)){
			toString = prefix + "-";
		}
		toString += super.toString();
		return toString;
	}
}
