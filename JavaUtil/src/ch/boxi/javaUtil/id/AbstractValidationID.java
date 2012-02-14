package ch.boxi.javaUtil.id;

public abstract class AbstractValidationID extends BaseID {
	private static final long serialVersionUID = -335558498096354448L;

	public AbstractValidationID(long dbRepresentive) {
		super(dbRepresentive);
		if(!isValid(dbRepresentive)){
			throw new ValidationException(dbRepresentiv + " is not a valid ID");
		}
	}
	
	public abstract boolean isValid(long id);
}
