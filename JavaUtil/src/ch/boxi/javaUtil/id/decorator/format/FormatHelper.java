package ch.boxi.javaUtil.id.decorator.format;

public class FormatHelper {
	public static int countDigits(String format){
		int count = 0;
		char lastChar = 'A'; // some non marker char
		for(char character: format.toCharArray()){
			if(lastChar != '\\' && (character == '0' || character == '#')){
				count++;
			}
			lastChar = character;
		}
		return count;
	}
}
