package ch.boxi.javaUtil.id.decorator.checkdigit.algorythms;


public abstract class AbstractCheckDigitAlgorythm implements CheckDigitAlgorythm {

	@Override
	public boolean isValidID(long id) {
		long checkdigit = id % getDigitMultiplicator();
		long number = (id - checkdigit) / getDigitMultiplicator();
		long calculatedCheckDigit = calcCheckDigit(number);
		return checkdigit == calculatedCheckDigit;
	}

	@Override
	public long AddCheckDigit(long id) {
		long checkDigit = calcCheckDigit(id);
		long number = id * getDigitMultiplicator() + checkDigit;
		return number;
	}
	
	@Override
	public long getNumber(long id) {
		long checkdigit = id % getDigitMultiplicator();
		long number = (id - checkdigit) / getDigitMultiplicator();
		return number;
	}

	@Override
	public long extractCheckDigit(long id) {
		long checkdigit = id % getDigitMultiplicator();
		return checkdigit;
	}
	
	private long getDigitMultiplicator(){
		Double pow = Math.pow(10, getCheckDigitSize());
		return pow.longValue(); 
	}
}
