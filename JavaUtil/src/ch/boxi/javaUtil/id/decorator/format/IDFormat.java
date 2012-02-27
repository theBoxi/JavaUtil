package ch.boxi.javaUtil.id.decorator.format;

import ch.boxi.javaUtil.id.ID;

public interface IDFormat {
	public int countDigits();
	public String formatID(ID id);
	public String getFormat();
}
