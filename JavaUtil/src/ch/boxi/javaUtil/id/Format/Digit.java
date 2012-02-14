package ch.boxi.javaUtil.id.Format;

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
}
