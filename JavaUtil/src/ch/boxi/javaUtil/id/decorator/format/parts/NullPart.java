package ch.boxi.javaUtil.id.decorator.format.parts;

public class NullPart extends FormatPart {

	public NullPart(int position) {
		super(position);
	}
	
	@Override
	public String toString(){
		return "";
	}

	@Override
	public FormatPart clone() {
		return new NullPart(position);
	}

}
