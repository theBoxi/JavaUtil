package ch.boxi.javaUtil.id.checkDigit;

public abstract class AbstractCheckDigitAlgorythm implements CheckDigitAlgorythm {

	@Override
	public boolean isValidID(long id) {
		long checkdigit = id % 10;
		long number = (id - checkdigit) / 10;
		long calculatedCheckDigit = calcCheckDigit(number);
		return checkdigit == calculatedCheckDigit;
	}

	@Override
	public long AddCheckDigit(long id) {
		long checkDigit = calcCheckDigit(id);
		long number = id * 10 + checkDigit;
		return number;
	}
	
	@Override
	public long getNumber(long id) {
		long checkdigit = id % 10;
		long number = (id - checkdigit) / 10;
		return number;
	}

	@Override
	public long extractCheckDigit(long id) {
		long checkdigit = id % 10;
		return checkdigit;
	}

	/**
	 * Calculate the CheckDigit for the id
	 * @param id real number without checkdigit in it
	 * @return the checkdigit
	 */
	public abstract long calcCheckDigit(long id);
	
	/**
	 * Returns the count of digits the check produces
	 * @return
	 */
	public abstract long getCheckDigitSize();
	
}
