package ch.boxi.javaUtil.id.decorator.prefix;

import org.apache.commons.lang3.StringUtils;

import ch.boxi.javaUtil.id.ID;
import ch.boxi.javaUtil.id.decorator.DecoratorType;
import ch.boxi.javaUtil.id.decorator.IDBaseDecorator;

public class PrefixDecorator extends IDBaseDecorator{
	private static final long serialVersionUID = 8187877066413541261L;
	
	protected final String prefix;
	
	public PrefixDecorator(ID baseID, String prefix) {
		super(baseID);
		this.prefix = prefix;
	}

	public String getPrefix() {
		return prefix;
	}
	
	@Override
	public DecoratorType getDecoratorType() {
		return DecoratorType.Prefix;
	}
	
	@Override
	public String toString(){
		String toString = "";
		if(StringUtils.isNotEmpty(prefix)){
			toString = prefix + "-";
		}
		toString += getBase().toString();
		return toString;
	}

	@Override
	public String getExtraValue() {
		return prefix;
	}
}
