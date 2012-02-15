package ch.boxi.javaUtil.id.Format;

import ch.boxi.javaUtil.id.BaseID;
import ch.boxi.javaUtil.id.PrefixedID;

public interface IDFormat {
	public int countDigits();
	public String formatID(BaseID id, String prefix);
	public String formatID(PrefixedID id);
	public String getFormat();
}
