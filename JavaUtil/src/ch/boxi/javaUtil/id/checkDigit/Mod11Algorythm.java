package ch.boxi.javaUtil.id.checkDigit;

public class Mod11Algorythm implements CheckDigitAlgorythm {

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
	public long calcCheckDigit(long id){
		String idString = Long.toString(id);
		long sum = 0;
		for(int i = 0; i < idString.length(); i++){
			long digit = Long.parseLong(idString.substring(i, i+1));
			int quantifier = i+2;
			sum += quantifier * digit;
		}
		long modulo11 = sum % 11;
		long checkDigit = 11 - modulo11;
		if(checkDigit == 10){
			checkDigit = 0;
		} else if(checkDigit == 11){
			checkDigit = 5;
		}
		return checkDigit;
	}
}
