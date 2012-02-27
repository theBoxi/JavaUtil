package ch.boxi.javaUtil.id.decorator.format.parts;

public abstract class FormatPart implements Comparable<FormatPart>{
	public int position;

	public FormatPart(int position){
		this.position = position;
	}
	
	public boolean isDigit(){
		return false;
	}
	
	@Override
	public int compareTo(FormatPart arg0) {
		return Integer.valueOf(position).compareTo(Integer.valueOf(arg0.position));
	}
	
	public boolean isPrefix(){
		return false;
	}
	
	public abstract FormatPart clone();
}
