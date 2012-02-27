package ch.boxi.javaUtil.id.decorator.format;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import ch.boxi.javaUtil.id.decorator.format.FormatException;
import ch.boxi.javaUtil.id.decorator.format.IDFormat;
import ch.boxi.javaUtil.id.decorator.format.SimpleIDFormat;
import ch.boxi.javaUtil.id.decorator.format.parts.Prefix;
import ch.boxi.javaUtil.id.decorator.prefix.PrefixDecorator;
import static org.junit.Assert.*;

public class SimpleIDFormatTest {
	@Test 
	public void testDigitCount(){
		assertEquals(4, new SimpleIDFormat("####").countDigits());
		assertEquals(4, new SimpleIDFormat("0###").countDigits());
		assertEquals(4, new SimpleIDFormat("00##").countDigits());
		assertEquals(4, new SimpleIDFormat("000#").countDigits());
		assertEquals(4, new SimpleIDFormat("0000").countDigits());
		
		assertEquals(4, new SimpleIDFormat("##.##").countDigits());
		assertEquals(4, new SimpleIDFormat("0#.##").countDigits());
		assertEquals(4, new SimpleIDFormat("\\# ##.##").countDigits());
		assertEquals(4, new SimpleIDFormat("\\# 00.##").countDigits());
		
		assertEquals(4, new SimpleIDFormat("\\0 ##.##").countDigits());
		assertEquals(4, new SimpleIDFormat("\\0 00.##").countDigits());
		
		assertEquals(6, new SimpleIDFormat("##-\\# ##.##").countDigits());
		assertEquals(6, new SimpleIDFormat("##-\\# 00.##").countDigits());
		
		assertEquals(6, new SimpleIDFormat("0#-\\0 ##.##").countDigits());
		assertEquals(6, new SimpleIDFormat("00-\\0 00.##").countDigits());
	}
	
	@Test 
	public void testDindNextUnquotedCharInFormat(){
		assertEquals(19, new SimpleIDFormat("sali{pre|prefix|suf}test{-|offcat}").findNextUnquotedCharInFormat(4, '}'));
		assertEquals(17, new SimpleIDFormat("sali{\\{|prefix|\\}}test{-|offcat}").findNextUnquotedCharInFormat(4, '}'));
		try{
			new SimpleIDFormat("sali{\\{|prefix|\\}test").findNextUnquotedCharInFormat(4, '}');
			assertTrue("FormatException expected", false);
		}catch(FormatException e){
			assertTrue(true);
		}catch(Exception e){
			assertTrue("FormatException expected", false);
		}
	}
	
	@Test
	public void testParseFormatPrefix(){
		Prefix prefix;
		prefix = new SimpleIDFormat("sali{du|prefix|da}test").parseFormatPrefix("{du|prefix|da}");
		assertPrefix(prefix, "du", "da");
		
		prefix = new SimpleIDFormat("sali{prefix|da}test").parseFormatPrefix("{prefix|da}");
		assertPrefix(prefix, "", "da");
		
		prefix = new SimpleIDFormat("sali{du|prefix}test").parseFormatPrefix("{du|prefix}");
		assertPrefix(prefix, "du", "");
		
		prefix = new SimpleIDFormat("sali{prefix}test").parseFormatPrefix("{prefix}");
		assertPrefix(prefix, "", "");
		
		prefix = new SimpleIDFormat("sali{pre|prefix|suf}test{-|offcat}").parseFormatPrefix("{pre|prefix|suf}");
		assertPrefix(prefix, "pre", "suf");
		
		prefix = new SimpleIDFormat("sali{\\{|prefix|\\}}test{-|offcat}").parseFormatPrefix("{\\{|prefix|\\}}");
		assertPrefix(prefix, "{", "}");
	}
	
	private void assertPrefix(Prefix prefix, String pre, String suf){
		assertEquals(StringUtils.isEmpty(prefix.PrePrefix), StringUtils.isEmpty(pre));
		if(StringUtils.isNotEmpty(pre)){
			assertEquals(pre, prefix.PrePrefix);
		}
		assertEquals(StringUtils.isEmpty(prefix.Sufix), StringUtils.isEmpty(suf));
		if(StringUtils.isNotEmpty(suf)){
			assertEquals(suf, prefix.Sufix);
		}
	}

	@Test
	public void testUnquot(){
		SimpleIDFormat idFormat = new SimpleIDFormat("sali{du|prefix|da}test");
		assertEquals("abc{\\ID", idFormat.unquot("abc\\{\\\\ID"));
	}
	
	@Test
	public void testFormat(){
		assertEquals("#ID-001.234", 	new SimpleIDFormat("{#|prefix|-}0##.###").formatID(new PrefixDecorator(new SimpleID(1234), "ID")));
		assertEquals("ID-1.234", 		new SimpleIDFormat("{prefix|-}###.###").formatID(new PrefixDecorator(new SimpleID(1234), "ID")));
		assertEquals("ID-123.456", 		new SimpleIDFormat("{prefix|-}###.###").formatID(new PrefixDecorator(new SimpleID(123456), "ID")));
		assertEquals("123.ID.456", 		new SimpleIDFormat("###.{prefix}.###").formatID(new PrefixDecorator(new SimpleID(123456), "ID")));
		assertEquals("001.234", 		new SimpleIDFormat("0##{.|prefix}.###").formatID(new PrefixDecorator(new SimpleID(1234), "")));
		
		try{
			new SimpleIDFormat("{prefix|-}###.###").formatID(new PrefixDecorator(new SimpleID(1234567), "ID"));
			assertTrue("Exception FormatException", false);
		}catch(FormatException e){
			assertTrue(true);
		}catch (Exception e) {
			assertTrue("Exception FormatException", false);
		}
	}
	
	@Test
	public void testBackslashQuots(){
		assertEquals("NR:#ID-001.234", 	new SimpleIDFormat("NR:\\#{prefix|-}0##.###").formatID(new PrefixDecorator(new SimpleID(1234), "ID")));
		assertEquals("#ID-001.234", 	new SimpleIDFormat("\\#{prefix|-}0##.###").formatID(new PrefixDecorator(new SimpleID(1234), "ID")));
		assertEquals("ID-001-{234}", 	new SimpleIDFormat("{prefix|-}0##-\\{###\\}").formatID(new PrefixDecorator(new SimpleID(1234), "ID")));
		assertEquals("{ID}-001-234", 	new SimpleIDFormat("{\\{|prefix|\\}-}0##-###").formatID(new PrefixDecorator(new SimpleID(1234), "ID")));
	}
	
	@Test
	public void testMultiFormat(){
		IDFormat idFormat = new SimpleIDFormat("{prefix|-}0##.###");
		assertEquals("SCN-012.345", idFormat.formatID(new PrefixDecorator(new SimpleID(12345), "SCN")));
		assertEquals("APL-001.234", idFormat.formatID(new PrefixDecorator(new SimpleID(1234), "APL")));
		
		idFormat = new SimpleIDFormat("{prefix|-}###.###");
		assertEquals("SCN-12.345", idFormat.formatID(new PrefixDecorator(new SimpleID(12345), "SCN")));
		assertEquals("1.234", idFormat.formatID(new PrefixDecorator(new SimpleID(1234), null)));
	}
}
