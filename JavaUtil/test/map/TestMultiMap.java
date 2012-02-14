/**
 * 
 */
package map;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ch.boxi.javaUtil.map.MultiMap;

/**
 * @author Boxi
 *
 */
public class TestMultiMap {

	private static final String key1 = "key1";
	private static final String key2 = "key2";
	private static final String value1 = "value1";
	private static final String value2 = "value2";
	private static final String value3 = "value3";
	
	private MultiMap<String, String> map;
	
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		map = new MultiMap<String, String>();		
	}
	
	@Test
	public void testSize(){
		map.put(key1, value1);
		map.put(key1, value3);
		map.put(key2, value2);
		
		assertEquals(3, map.size());
	}
	
	@Test
	public void testMultiAdd(){
		map.put(key1, value1);
		map.put(key1, value2);
		List<String> list = map.get(key1);
		assertTrue(list.contains(value1));
		assertTrue(list.contains(value2));
	}
	
	@Test
	public void testAdd(){
		map.put(key1, value1);
		map.put(key2, value2);
		assertEquals(value1, map.get(key1).iterator().next());
		assertEquals(value2, map.get(key2).iterator().next());
	}
	
	@Test
	public void testRemoveByKey(){
		map.put(key1, value1);
		map.put(key1, value3);
		map.put(key2, value2);
		
		assertEquals(3, map.size());
		
		map.remove(key1);
		
		assertEquals(1, map.size());
	}
	
	@Test
	public void testRemoveByKeyValue(){
		map.put(key1, value1);
		map.put(key1, value3);
		map.put(key2, value2);
		
		assertEquals(3, map.size());
		
		map.remove(key1, value1);
		
		assertEquals(2, map.size());
		assertEquals(1, map.get(key1).size());
		assertEquals(value3, map.get(key1).iterator().next());
		

		map.remove(key2, value2);
		
		assertEquals(1, map.size());
		assertEquals(0, map.get(key2).size());
	}
	
	@Test
	public void testGetNull(){
		assertNotNull(map.get(key1));
	}
}
