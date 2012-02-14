package ch.boxi.javaUtil.id.Format;

public class StaticString extends FormatPart{
	public String string;
	
	public StaticString(int position) {
		super(position);
	}
	
	@Override
	public String toString(){
		return string;
	}
}
