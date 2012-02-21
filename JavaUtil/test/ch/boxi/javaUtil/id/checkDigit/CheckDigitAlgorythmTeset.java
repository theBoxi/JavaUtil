package ch.boxi.javaUtil.id.checkDigit;

import org.junit.Assert;
import org.junit.Test;

public class CheckDigitAlgorythmTeset {

	@Test
	public void testMod11Algo(){
		Mod11Algorythm mod11 = new Mod11Algorythm();
		
		long idwithoutDigit = 1234l;
		long idwithdigit = mod11.AddCheckDigit(idwithoutDigit);
		Assert.assertTrue(mod11.isValidID(idwithdigit));
		Assert.assertFalse(mod11.isValidID(idwithdigit + 10));
		
		String idWith = Long.toString(idwithdigit);
		String idWithout = Long.toString(idwithoutDigit);
		Assert.assertTrue(idWith.startsWith(idWithout));
		
		Assert.assertEquals(idwithoutDigit, mod11.getNumber(idwithdigit));
		Assert.assertEquals(idwithdigit, idwithoutDigit*10 + mod11.extractCheckDigit(idwithdigit));
	}
}
