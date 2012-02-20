package ch.boxi.javaUtil.id.Format;

import org.apache.commons.lang3.StringUtils;

public class Prefix extends FormatPart{
	public String PrePrefix;
	public String Prefix;
	public String Sufix;
	
	public Prefix(int position) {
		super(position);
	}
	
	@Override
	public String toString(){
		if(StringUtils.isNotEmpty(Prefix)){
			return PrePrefix + Prefix + Sufix;
		}
		return "";
	}

	@Override
	public FormatPart clone() {
		Prefix clone = new Prefix(position);
		clone.PrePrefix = PrePrefix;
		clone.Prefix = Prefix;
		clone.Sufix = Sufix;
		return clone;
	}
	
	@Override
	public boolean isPrefix(){
		return true;
	}
}
