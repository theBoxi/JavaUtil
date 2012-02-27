package ch.boxi.javaUtil.id.decorator.checkdigit.algorythms;


public class Mod11Algorythm extends AbstractCheckDigitAlgorythm{

	@Override
	public long calcCheckDigit(long id){
		String idString = Long.toString(id);
		long sum = 0;
		for(int i = 0; i < idString.length(); i++){
			long digit = Long.parseLong(idString.substring(i, i+1));
			int quantifier = i+1;
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

	@Override
	public long getCheckDigitSize() {
		return 1;
	}
}
