package ch.boxi.javaUtil.id.decorator.checkdigit;

import ch.boxi.javaUtil.id.ID;
import ch.boxi.javaUtil.id.decorator.DecoratorType;
import ch.boxi.javaUtil.id.decorator.IDBaseDecorator;
import ch.boxi.javaUtil.id.decorator.checkdigit.algorythms.CheckDigitAlgorythm;

public class CheckDigitDecorator extends IDBaseDecorator {
	private static final long serialVersionUID = -5361886048369968087L;
	
	protected CheckDigitAlgorythm algorythm;
	
	protected Long checkDigit;
	
	public CheckDigitDecorator(ID baseID, CheckDigitAlgorythm algorythm) {
		super(baseID);
		this.algorythm = algorythm;
		this.checkDigit = this.algorythm.extractCheckDigit(baseID.getLongValue());
	}
	
	public CheckDigitAlgorythm getCheckDigitAlgorythm(){
		return algorythm;
	}
	
	public long getCheckDigit(){
		return algorythm.extractCheckDigit(getLongValue());
	}
	
	public long getNumber(){
		return algorythm.getNumber(getLongValue());
	}
	
	@Override
	public DecoratorType getDecoratorType() {
		return DecoratorType.CeckDigit;
	}
	
	@Override
	public String getExtraValue() {
		return checkDigit.toString();
	}
	
	@Override
	public String toString(){
		return algorythm.getNumber(getBase().getLongValue()) + "." + checkDigit;
	}
}
