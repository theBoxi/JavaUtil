package ch.boxi.javaUtil.id.decorator.format;

import ch.boxi.javaUtil.id.BaseID;
import ch.boxi.javaUtil.id.decorator.IDBaseDecorator;

public abstract class FormatterDecorator extends IDBaseDecorator{
	private static final long serialVersionUID = -2550885611584416746L;

	protected final IDFormat format;
		
	public FormatterDecorator(BaseID baseID, IDFormat format) {
		super(baseID);
		this.format = format;
	}
	
	public IDFormat getFormat(){
		return format;
	}
	
	@Override
	public String toString(){
		return format.formatID(this);
	}
}
