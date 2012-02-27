package ch.boxi.javaUtil.id.decorator.checkdigit.algorythms;


/**
 * ISO 7064 Mod 97,10
 *
 * This scheme produces two digits as a check. And as a result, it catches just about every possible error. 
 * If you can afford the extra digit, this system is superior to the dihedral check.
 *
 * It also has an especially compact formula. The check digits are given by 
 * mod(98 - mod(data * 100, 97), 97) and the verification is just mod(data_check,97) == 1. 
 * In practice an alternate algorithm (based on Horner's Rule) is used to avoid overflow issues. 
 */
public class Mod9710Algorythm extends AbstractCheckDigitAlgorythm{

	/*
	 * For realy big Numbers it is possible to split the numbers in 9 Digit numbers an mod97 it each alone
	 * 
	 * 200800000970375700131400 % 97 = 10
	 * 
	 * 200800000970375700131400 will be 200800000 97037570 0131400
	 * 200800000 % 97 = 9   aad this 9 to the next numberblock
	 * 997037570 % 97 = 81  and the same again
	 * 810131400 % 97 = 10  -> finished
	 */
	
	@Override
	public long calcCheckDigit(long id) {
		long checkDigits = mod(98 - mod(id * 100, 97), 97);
		return checkDigits;
	}

	@Override
	public long getCheckDigitSize() {
		return 2;
	}
	
	@Override
	public boolean isValidID(long id) {
		return mod(id, 97) == 1;
	}

	private long mod(long x, long y){
		return x % y;
	}
}
