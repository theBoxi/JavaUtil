package ch.boxi.javaUtil.id.decorator.validation;

import ch.boxi.javaUtil.id.ID;
import ch.boxi.javaUtil.id.decorator.IDBaseDecorator;

public abstract class AbstractValidationID extends IDBaseDecorator{
	private static final long serialVersionUID = 6905628844547364773L;
	
	public AbstractValidationID(ID baseID) {
		super(baseID);
	}
	
	public abstract boolean isValid();

	public void assertIsValid(){
		if(!isValid()){
			throw new ValidationException(getBase().getLongValue() + " is not a valid id");
		}
	}
}
