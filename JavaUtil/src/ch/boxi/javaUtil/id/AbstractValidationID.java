package ch.boxi.javaUtil.id;

public abstract class AbstractValidationID extends PrefixedID{
	private static final long serialVersionUID = 6905628844547364773L;
	
	public AbstractValidationID(String prefix, long dbRepresentive) {
		super(prefix, dbRepresentive);
	}
	
	public abstract boolean isValid();
	

	public void assertIsValid(){
		if(!isValid()){
			throw new ValidationException(dbRepresentiv + " is not a valid id");
		}
	}
}
