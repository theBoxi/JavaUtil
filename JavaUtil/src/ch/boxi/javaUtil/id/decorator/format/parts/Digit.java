package ch.boxi.javaUtil.id.decorator.format.parts;

public class Digit extends FormatPart{
	public String digit = "";
	
	public Digit(int position) {
		super(position);
	}
	
	@Override
	public boolean isDigit(){
		return true;
	}
	
	@Override
	public String toString(){
		return digit;
	}

	@Override
	public FormatPart clone() {
		return new Digit(position);
	}
}
