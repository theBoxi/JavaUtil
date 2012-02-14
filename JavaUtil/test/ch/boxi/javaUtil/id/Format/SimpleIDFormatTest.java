package ch.boxi.javaUtil.id.Format;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import ch.boxi.javaUtil.id.BaseID;
import ch.boxi.javaUtil.id.Format.SimpleIDFormat;
import ch.boxi.javaUtil.id.Format.Prefix;
import static org.junit.Assert.*;

public class SimpleIDFormatTest {
	@Test 
	public void testDigitCount(){
		assertEquals(4, new SimpleIDFormat("####", "blabla").countDigits());
		assertEquals(4, new SimpleIDFormat("0###", "blabla").countDigits());
		assertEquals(4, new SimpleIDFormat("00##", "blabla").countDigits());
		assertEquals(4, new SimpleIDFormat("000#", "blabla").countDigits());
		assertEquals(4, new SimpleIDFormat("0000", "blabla").countDigits());
		
		assertEquals(4, new SimpleIDFormat("##.##", "blabla").countDigits());
		assertEquals(4, new SimpleIDFormat("0#.##", "blabla").countDigits());
		assertEquals(4, new SimpleIDFormat("\\# ##.##", "blabla").countDigits());
		assertEquals(4, new SimpleIDFormat("\\# 00.##", "blabla").countDigits());
		
		assertEquals(4, new SimpleIDFormat("\\0 ##.##", "blabla").countDigits());
		assertEquals(4, new SimpleIDFormat("\\0 00.##", "blabla").countDigits());
		
		assertEquals(6, new SimpleIDFormat("##-\\# ##.##", "blabla").countDigits());
		assertEquals(6, new SimpleIDFormat("##-\\# 00.##", "blabla").countDigits());
		
		assertEquals(6, new SimpleIDFormat("0#-\\0 ##.##", "blabla").countDigits());
		assertEquals(6, new SimpleIDFormat("00-\\0 00.##", "blabla").countDigits());
	}
	
	@Test 
	public void testDindNextUnquotedCharInFormat(){
		assertEquals(19, new SimpleIDFormat("sali{pre|prefix|suf}test{-|offcat}", "prefix").findNextUnquotedCharInFormat(4, '}'));
		assertEquals(17, new SimpleIDFormat("sali{\\{|prefix|\\}}test{-|offcat}", "prefix").findNextUnquotedCharInFormat(4, '}'));
		try{
			new SimpleIDFormat("sali{\\{|prefix|\\}test", "prefix").findNextUnquotedCharInFormat(4, '}');
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
		prefix = new SimpleIDFormat("sali{du|prefix|da}test", "prefix").parseFormatPrefix("{du|prefix|da}");
		assertPrefix(prefix, "du", "da");
		
		prefix = new SimpleIDFormat("sali{prefix|da}test", "prefix").parseFormatPrefix("{prefix|da}");
		assertPrefix(prefix, "", "da");
		
		prefix = new SimpleIDFormat("sali{du|prefix}test", "prefix").parseFormatPrefix("{du|prefix}");
		assertPrefix(prefix, "du", "");
		
		prefix = new SimpleIDFormat("sali{prefix}test", "prefix").parseFormatPrefix("{prefix}");
		assertPrefix(prefix, "", "");
		
		prefix = new SimpleIDFormat("sali{du|prefix|da}test", "").parseFormatPrefix("{du|prefix|da}");
		assertPrefix(prefix, "", "");
		assertTrue(StringUtils.isEmpty(prefix.toString()));
		
		prefix = new SimpleIDFormat("sali{pre|prefix|suf}test{-|offcat}", "prefix").parseFormatPrefix("{pre|prefix|suf}");
		assertPrefix(prefix, "pre", "suf");
		
		prefix = new SimpleIDFormat("sali{\\{|prefix|\\}}test{-|offcat}", "prefix").parseFormatPrefix("{\\{|prefix|\\}}");
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
		SimpleIDFormat idFormat = new SimpleIDFormat("sali{du|prefix|da}test", "");
		assertEquals("abc{\\ID", idFormat.unquot("abc\\{\\\\ID"));
	}
	
	@Test
	public void testFormat(){
		assertEquals("#ID-001.234", 	new SimpleIDFormat("{#|prefix|-}0##.###", "ID").formatID(new SimpleID(1234)));
		assertEquals("ID-1.234", 		new SimpleIDFormat("{prefix|-}###.###", "ID").formatID(new SimpleID(1234)));
		assertEquals("ID-123.456", 		new SimpleIDFormat("{prefix|-}###.###", "ID").formatID(new SimpleID(123456)));
		assertEquals("123.ID.456", 		new SimpleIDFormat("###.{prefix}.###", "ID").formatID(new SimpleID(123456)));
		assertEquals("001.234", 		new SimpleIDFormat("0##{.|prefix}.###", "").formatID(new SimpleID(1234)));
		
		try{
			new SimpleIDFormat("{prefix|-}###.###", "ID").formatID(new SimpleID(1234567));
			assertTrue("Exception FormatException", false);
		}catch(FormatException e){
			assertTrue(true);
		}catch (Exception e) {
			assertTrue("Exception FormatException", false);
		}
	}
	
	@Test
	public void testBackslashQuots(){
		assertEquals("NR:#ID-001.234", 	new SimpleIDFormat("NR:\\#{prefix|-}0##.###", "ID").formatID(new SimpleID(1234)));
		assertEquals("#ID-001.234", 	new SimpleIDFormat("\\#{prefix|-}0##.###", "ID").formatID(new SimpleID(1234)));
		assertEquals("ID-001-{234}", 	new SimpleIDFormat("{prefix|-}0##-\\{###\\}", "ID").formatID(new SimpleID(1234)));
		assertEquals("{ID}-001-234", 	new SimpleIDFormat("{\\{|prefix|\\}-}0##-###", "ID").formatID(new SimpleID(1234)));
	}
	
	@Test
	public void testMultiFormat(){
		IDFormat idFormat = new SimpleIDFormat("{prefix|-}0##.###", "SCN");
		assertEquals("SCN-012.345", idFormat.formatID(new SimpleID(12345)));
		assertEquals("SCN-001.234", idFormat.formatID(new SimpleID(1234)));
		
		idFormat = new SimpleIDFormat("{prefix|-}###.###", "SCN");
		assertEquals("SCN-12.345", idFormat.formatID(new SimpleID(12345)));
		assertEquals("SCN-1.234", idFormat.formatID(new SimpleID(1234)));
	}
	
	public class SimpleID extends BaseID{
		private static final long serialVersionUID = -669297720069697713L;
		
		public SimpleID(long dbRepresentive) {
			super(dbRepresentive);
		}
	};
}
