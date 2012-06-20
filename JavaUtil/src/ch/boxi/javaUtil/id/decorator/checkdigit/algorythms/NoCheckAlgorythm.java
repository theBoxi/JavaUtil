package ch.boxi.javaUtil.id.decorator.checkdigit.algorythms;

public class NoCheckAlgorythm extends AbstractCheckDigitAlgorythm {

	@Override
	public long getCheckDigitSize() {
		return 0;
	}

	@Override
	public long calcCheckDigit(long id) {
		return 0;
	}

}
