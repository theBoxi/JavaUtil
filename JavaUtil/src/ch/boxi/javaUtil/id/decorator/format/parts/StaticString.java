package ch.boxi.javaUtil.id.decorator.format.parts;

public class StaticString extends FormatPart{
	public String string;
	
	public StaticString(int position) {
		super(position);
	}
	
	@Override
	public String toString(){
		return string;
	}

	@Override
	public FormatPart clone() {
		StaticString clone = new StaticString(position);
		clone.string = string;
		return clone;
	}
}
