package ch.boxi.javaUtil.id.Format;

import org.apache.commons.lang3.StringUtils;

public class Offcut extends FormatPart{
	public String prefix;
	public String offcutDigits;
	public String suffix;

	public Offcut(int position) {
		super(position);
	}
	
	@Override
	public String toString(){
		if(StringUtils.isNotEmpty(offcutDigits)){
			return prefix + offcutDigits + suffix;
		}
		return "";
	}
}
