package ch.boxi.javaUtil.id;

import ch.boxi.javaUtil.id.Format.IDFormat;

public abstract class FormattedID extends AbstractValidationID{
	private static final long serialVersionUID = -2550885611584416746L;

	protected final IDFormat format;
		
	public FormattedID(String prefix, long dbRepresentive, IDFormat format) {
		super(prefix, dbRepresentive);
		this.format = format;
		if(!isValid()){
			throw new ValidationException(dbRepresentiv + " is not a valid ID for format: " + format.getFormat());
		}
	}
	
	public IDFormat getFormat(){
		return format;
	}
	
	@Override
	public boolean isValid() {
		return Long.toString(dbRepresentiv).length() <= format.countDigits();
	}
	
	@Override
	public String toString(){
		return format.formatID(this);
	}
}
