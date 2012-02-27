package ch.boxi.javaUtil.id.decorator;

import ch.boxi.javaUtil.id.ID;

public abstract class IDBaseDecorator implements ID {
	private static final long serialVersionUID = -9107488440729659569L;
	
	private ID baseID;
	
	public IDBaseDecorator(ID baseID) {
		super();
		this.baseID = baseID;
	}

	public ID getBase(){
		return baseID;
	}
	
	@Override
	public String toString(){
		return baseID.toString();
	}

	@Override
	public int compareTo(ID o) {
		return baseID.compareTo(o);
	}

	@Override
	public long getLongValue() {
		return baseID.getLongValue();
	}
	
	public abstract DecoratorType getDecoratorType();
	
	public abstract String getExtraValue();

}
