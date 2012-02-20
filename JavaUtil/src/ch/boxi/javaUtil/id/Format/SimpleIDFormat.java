package ch.boxi.javaUtil.id.Format;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import ch.boxi.javaUtil.id.BaseID;
import ch.boxi.javaUtil.id.PrefixedID;

/**
 * format like {pre|prefix|suf}0##.###-###
 * where: 
 * 	-  {prefix} will be replaced with the prefix and every thing behind the | in {prefx|ABC} is only printed if a prefix is set.
 *  -  0 marks that the number is filled with leading zeros
 *  -  # marks a singel digit
 *  
 *  <b>!!! SimpleIDFormat is not threadSafe !!!</b>
 *  
 *  Exp: 
 *     format: "{prefix|-}0##.###.###"
 *     prefix: "PID"
 *     dbRepresentive: "1234"
 *     \=> PID-000.001.234
 *     
 *     format: "{prefix|-}###.###.###"
 *     prefix: ""
 *     dbRepresentive: "1234"
 *     \=> .1.234
 *     
 *     format: "0########"
 *     prefix: ""
 *     dbRepresentive: "1234"
 *     \=> 000001234
 */
public class SimpleIDFormat implements IDFormat{
	
	private String format;
	private boolean fillInLeadingZeros = false;
	private LinkedList<FormatPart> parts = new LinkedList<FormatPart>();
	private Prefix prefixPart = null;
	
	public SimpleIDFormat(String format){
		this.format = format;
		parseFormat();
	}
	
	@Override
	public int countDigits(){
		int count = 0;
		for(FormatPart part: parts){
			if(part.isDigit()){
				count++;
			}
		}
		return count;
	}
	
	@Override
	public String getFormat(){
		return format;
	}
	
	Prefix parseFormatPrefix(String formatSubString){
		Prefix returnPrefix = new Prefix(0);
		Pattern pattern = Pattern.compile("(\\{(.*)\\|?prefix\\|?(.*)\\})");
		Matcher matcher = pattern.matcher(formatSubString);
		if(matcher.matches()){
			int position = format.indexOf(matcher.group(1));
			returnPrefix = new Prefix(position);
			returnPrefix.PrePrefix = matcher.group(2);
			if(StringUtils.isNotEmpty(returnPrefix.PrePrefix)){
				returnPrefix.PrePrefix = unquot(returnPrefix.PrePrefix.substring(0, returnPrefix.PrePrefix.length()-1));
			}
			returnPrefix.Sufix = unquot(matcher.group(3));
		}
		return returnPrefix;
	}
	
	String unquot(String string){
		char lastChar = 'A'; // some non marker char
		char[] charArray = string.toCharArray();
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < charArray.length; i++){
			if(charArray[i] == '\\'  && lastChar != '\\'){
				//mark
				lastChar = charArray[i];
			}else if(lastChar == '\\'){
				sb.append(charArray[i]);
				if(charArray[i] == '\\'){
					lastChar = 'A'; // some non marker char
				} else{
					lastChar = charArray[i];
				}
			} else{
				sb.append(charArray[i]);
			}
		}
		return sb.toString();
	}
	
	void parseFormat(){
		char lastChar = 'A'; // some non marker char
		char[] charArray = format.toCharArray();
		for(int i = 0; i < charArray.length; i++){
			FormatPart part = new NullPart(i);
			if(lastChar == '\\'){
				StaticString ssPart = new StaticString(i);
				ssPart.string = "" + charArray[i];
				part = ssPart;
			}else if(charArray[i] == '\\'){
				// noop - wait for next char.
			}else if(charArray[i] == '0'){
				fillInLeadingZeros = true;
				part = new Digit(i);
			}else if(charArray[i] == '#'){
				part = new Digit(i);
			}else if(charArray[i] == '{'){
				int end = findNextUnquotedCharInFormat(i, '}');
				prefixPart = parseFormatPrefix(format.substring(i, end+1)); 
				part = prefixPart; 
				i = end;
			}else{
				StaticString ssPart = new StaticString(i);
				ssPart.string = "" + charArray[i];
				part = ssPart;
			}
			parts.add(part);
			lastChar = charArray[i];
		}
		Collections.sort(parts);
	}
	
	int findNextUnquotedCharInFormat(int startPos, char c){
		int pos = StringUtils.indexOf(format, c, startPos);
		if(pos < startPos  || pos < 0){
			throw new FormatException("invalid format: no closeChar " + c + " found for position " + startPos);
		}
		if(format.charAt(pos - 1) != '\\'){
			return pos;
		}else{
			return findNextUnquotedCharInFormat(pos+1, c);
		}
	}

	@Override
	public String formatID(BaseID id, String prefix) {
		LinkedList<FormatPart> partClones = cloneParts();
		String idString = Long.toString(id.getLongValue());
		if(countDigits() < idString.length()){
			throw new FormatException("id to long");
		}
		setPrefixIntoClonedParts(partClones, prefix);
		int idStringPointer = idString.length() - 1;
		Iterator<FormatPart> iterator = partClones.descendingIterator();
		while(iterator.hasNext()){
			FormatPart part = iterator.next();
			if(part.isDigit()){
				Digit digit = (Digit)part;
				if(idStringPointer < 0){
					if(fillInLeadingZeros){
						digit.digit = "0";
					}else{
						digit.digit = "";
					}
				}else{
					digit.digit = "" + idString.charAt(idStringPointer--);
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for(FormatPart part: partClones){
			sb.append(part.toString());
		}
		return sb.toString();
	}
	
	private void setPrefixIntoClonedParts(LinkedList<FormatPart> clonedParts, String prefix){
		for(FormatPart part: clonedParts){
			if(part.isPrefix()){
				Prefix prefixPart = (Prefix) part;
				prefixPart.Prefix = prefix;
			}
		}
	}
	
	private LinkedList<FormatPart> cloneParts(){
		LinkedList<FormatPart> newParts = new LinkedList<FormatPart>();
		for(FormatPart part: parts){
			newParts.add(part.clone());
		}
		return newParts;
	}

	@Override
	public String formatID(PrefixedID id){
		return formatID(id, id.getPrefix());
	}
	
	@Override
	public String toString(){
		return format;
	}
}
