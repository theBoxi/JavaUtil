package ch.boxi.javaUtil.id.checkDigit;

import ch.boxi.javaUtil.id.AbstractValidationID;

public abstract class CheckDigitID extends AbstractValidationID {
	private static final long serialVersionUID = -5361886048369968087L;

	protected CheckDigitAlgorythm algorythm;
	
	public CheckDigitID(CheckDigitAlgorythm algorythm, String prefix, long dbRepresentive) {
		super(prefix, dbRepresentive);
		this.algorythm = algorythm;
	}
	
	public CheckDigitAlgorythm getCheckDigitAlgorythm(){
		return algorythm;
	}
	
	public long getCheckDigit(){
		return algorythm.extractCheckDigit(dbRepresentiv);
	}
	
	public long getNumber(){
		return algorythm.getNumber(dbRepresentiv);
	}

	@Override
	public boolean isValid() {
		return algorythm.isValidID(dbRepresentiv);
	}

}
