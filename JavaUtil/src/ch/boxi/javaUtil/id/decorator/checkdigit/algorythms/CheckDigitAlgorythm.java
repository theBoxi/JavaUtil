package ch.boxi.javaUtil.id.decorator.checkdigit.algorythms;

public interface CheckDigitAlgorythm {
	/**
	 * Split number from ID-WithCheckDigits
	 * @param id
	 * @returs number without the checkdigits	
	 */
	public long getNumber(long id);
	
	/**
	 * Split checkDigits from id
	 * @param id
	 * @return checkdigits
	 */
	public long extractCheckDigit(long id);
	
	/**
	 * Checks if this ID-With-CheckDigis is Valid. Split number an checkdigits and test it.
	 * @param id
	 * @return true if valid, otherwise false ;-)
	 */
	public boolean isValidID(long id);
	
	/**
	 * Add Checkdigits to the ID
	 * @param id ID-without Checkdigits
	 * @return ID-With Checkdigits
	 */
	public long AddCheckDigit(long id);
	
	/**
	 * Returns the count of checkDigits
	 * @return
	 */
	public long getCheckDigitSize();
}
