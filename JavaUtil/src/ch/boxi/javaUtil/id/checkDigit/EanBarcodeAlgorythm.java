package ch.boxi.javaUtil.id.checkDigit;

/**
 * Die Prüfziffer der EAN-Nummern (13. Ziffer).
 * Berechnet sich, indem man die ersten zwölf Ziffern abwechselnd mit 1 und 3 multipliziert (links mit 1 anfangen) 
 * und diese Produkte summiert. Die Prüfziffer ist die Differenz der Summe zum nächsten Vielfachen von 10. Falls 
 * die Summe durch 10 teilbar ist, ist die Prüfziffer die 0.
 * Bei ILN- oder NVE-Nummern wird dasselbe Verfahren angewendet.
 * 
 * @author tzhbosa5
 *
 */
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
