package ch.boxi.javaUtil.id.checkDigit;

public interface CheckDigitAlgorythm {
	public long getNumber(long id);
	public long extractCheckDigit(long id);
	public boolean isValidID(long id);
	public long AddCheckDigit(long id);
}
