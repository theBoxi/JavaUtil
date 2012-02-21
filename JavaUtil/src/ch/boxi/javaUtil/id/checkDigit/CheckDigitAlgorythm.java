package ch.boxi.javaUtil.id.checkDigit;

public interface CheckDigitAlgorythm {
	public boolean isValidID(long id);
	public long AddCheckDigit(long id);
}
