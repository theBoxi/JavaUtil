package ch.boxi.javaUtil.id.decorator.format;

import ch.boxi.javaUtil.id.ID;
import ch.boxi.javaUtil.id.decorator.DecoratorType;
import ch.boxi.javaUtil.id.decorator.IDBaseDecorator;

public class FormatterDecorator extends IDBaseDecorator{
	private static final long serialVersionUID = -2550885611584416746L;

	protected final IDFormat format;
		
	public FormatterDecorator(ID baseID, IDFormat format) {
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

	@Override
	public DecoratorType getDecoratorType() {
		return DecoratorType.Format;
	}

	@Override
	public String getExtraValue() {
		return "";
	}
}
