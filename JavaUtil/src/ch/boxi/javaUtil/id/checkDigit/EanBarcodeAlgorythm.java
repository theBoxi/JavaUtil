package ch.boxi.javaUtil.id.checkDigit;

public class EanBarcodeAlgorythm extends AbstractCheckDigitAlgorythm{

	@Override
	public long calcCheckDigit(long id) {
		Integer.parseInt(String.valueOf(Long.toString(id).charAt(0)));
		String number = Long.toString(id);
		int sum = 0;
		for(int i = 0; i < number.length(); i++){
			int digit = Integer.parseInt(String.valueOf(number.charAt(i)));
			sum += digit * calcMultiplicator(i);
		}
		
		// difference to the next 10er Number
		long checkdigit = 10 - (sum %10);
		if(checkdigit == 10){
			checkdigit = 0;
		}
		
		return checkdigit;
	}
	
	private int calcMultiplicator(int index){
		int rest = index % 2;
		if(rest == 0){
			return 1;
		} else{
			return 3;
		}
	}
	
	@Override
	public long getCheckDigitSize() {
		return 1;
	}
}
