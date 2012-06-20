package ch.boxi.javaUtil.id.checkDigit;

import org.junit.Assert;
import org.junit.Test;

import ch.boxi.javaUtil.id.decorator.checkdigit.algorythms.CheckDigitAlgorythm;
import ch.boxi.javaUtil.id.decorator.checkdigit.algorythms.Mod11Algorythm;
import ch.boxi.javaUtil.id.decorator.checkdigit.algorythms.Mod9710Algorythm;
import ch.boxi.javaUtil.id.decorator.checkdigit.algorythms.NoCheckAlgorythm;

public class CheckDigitAlgorythmTeset {

	@Test
	public void testMod11Algo(){
		assertCheckDigitAlgorythm(new Mod11Algorythm(), true);
	}
	
	@Test
	public void testMod9710Algorythm(){
		assertCheckDigitAlgorythm(new Mod9710Algorythm(), true);
	}
	
	@Test
	public void testNoCheckAlgorythm(){
		assertCheckDigitAlgorythm(new NoCheckAlgorythm(), false);
	}
	
	public void assertCheckDigitAlgorythm(CheckDigitAlgorythm algorythm, boolean doBadChecks){
		long idwithoutDigit = 1234l;
		long idwithdigit = algorythm.AddCheckDigit(idwithoutDigit);
		Assert.assertTrue(algorythm.isValidID(idwithdigit));
		if(doBadChecks){
			Assert.assertFalse(algorythm.isValidID(idwithdigit + 10));
		}
		
		String idWith = Long.toString(idwithdigit);
		String idWithout = Long.toString(idwithoutDigit);
		Assert.assertTrue(idWith.startsWith(idWithout));
		
		Assert.assertEquals(idwithoutDigit, algorythm.getNumber(idwithdigit));
		Assert.assertEquals(idwithdigit, idwithoutDigit * getDigitMultiplicator(algorythm.getCheckDigitSize()) + algorythm.extractCheckDigit(idwithdigit));
	}
	
	private long getDigitMultiplicator(long size){
		Double pow = Math.pow(10, size);
		return pow.longValue(); 
	}
}
