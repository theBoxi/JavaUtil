/**
 * 
 */
package tree;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import ch.boxi.javaUtil.tree.DefaultNodeHolder;
import ch.boxi.javaUtil.tree.TraversationalOrder;

/**
 * @author Boxi
 *
 */
public class NodeTest {
	
	private DefaultNodeHolder<String> tree;
	
	private DefaultNodeHolder<String> layer1node1 = new DefaultNodeHolder<String>("1.1");
	private DefaultNodeHolder<String> layer1node2 = new DefaultNodeHolder<String>("1.2");
	
	private DefaultNodeHolder<String> layer2node1 = new DefaultNodeHolder<String>("1.1.1");
	private DefaultNodeHolder<String> layer2node2 = new DefaultNodeHolder<String>("1.1.2");
	
	private DefaultNodeHolder<String> layer2node3 = new DefaultNodeHolder<String>("1.2.1");
	private DefaultNodeHolder<String> layer2node4 = new DefaultNodeHolder<String>("1.2.2");

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		tree = new DefaultNodeHolder<String>(null, "rootNode");
		
		tree.addChild(layer1node1);
		layer1node1.addChild(layer2node1);
		layer1node1.addChild(layer2node2);
		
		tree.addChild(layer1node2);
		layer1node2.addChild(layer2node3);
		layer1node2.addChild(layer2node4);
	}
	
	@Test
	public void testInitSize(){
		tree = new DefaultNodeHolder<String>(null, "rootNode");
		
		assertEquals(1, tree.size());
	}
	
	@Test
	public void testAddNode(){
		tree = new DefaultNodeHolder<String>(null, "rootNode");
		
		assertTrue(tree.addChild(new DefaultNodeHolder<String>("1st")));
		assertEquals(2, tree.size());
	}
	
	@Test
	public void testMultiLayerdAdd(){
		tree = new DefaultNodeHolder<String>(null, "rootNode");
		
		DefaultNodeHolder<String> layer1node1 = new DefaultNodeHolder<String>("1.1");
		DefaultNodeHolder<String> layer1node2 = new DefaultNodeHolder<String>("1.2");
		
		DefaultNodeHolder<String> layer2node1 = new DefaultNodeHolder<String>("1.1.1");
		DefaultNodeHolder<String> layer2node2 = new DefaultNodeHolder<String>("1.1.2");
		
		DefaultNodeHolder<String> layer2node3 = new DefaultNodeHolder<String>("1.2.1");
		DefaultNodeHolder<String> layer2node4 = new DefaultNodeHolder<String>("1.2.2");
		
		
		assertTrue(tree.addChild(layer1node1));
		assertTrue(layer1node1.addChild(layer2node1));
		assertTrue(layer1node1.addChild(layer2node2));
		
		assertEquals(4, tree.size());
		assertEquals(3, layer1node1.size());
		assertEquals(1, layer2node1.size());
		assertEquals(1, layer2node2.size());
		
		
		assertTrue(layer1node2.addChild(layer2node3));
		assertTrue(layer1node2.addChild(layer2node4));
		assertTrue(tree.addChild(layer1node2));
		
		assertEquals(7, tree.size());
		assertEquals(3, layer1node2.size());
	}
	
	@Test
	public void testDepthFirstIteratorPreOrder(){
		LinkedList<DefaultNodeHolder<String>> order = new LinkedList<DefaultNodeHolder<String>>();
		order.addLast(tree);
		order.addLast(layer1node1);
		order.addLast(layer2node1);
		order.addLast(layer2node2);
		order.addLast(layer1node2);
		order.addLast(layer2node3);
		order.addLast(layer2node4);
		
		testIterator(tree.iteratorDepthFirtst(TraversationalOrder.PreOrder), order);
	}
	
	@Test
	public void testDepthFirstIteratorPostOrder(){
		LinkedList<DefaultNodeHolder<String>> order = new LinkedList<DefaultNodeHolder<String>>();
		order.addLast(layer2node1);
		order.addLast(layer2node2);
		order.addLast(layer1node1);
		
		order.addLast(layer2node3);
		order.addLast(layer2node4);
		order.addLast(layer1node2);
		
		order.addLast(tree);
		
		testIterator(tree.iteratorDepthFirtst(TraversationalOrder.PostOrder), order);
	}
	
	@Test
	public void testBreadthFirstIterator(){
		LinkedList<DefaultNodeHolder<String>> order = new LinkedList<DefaultNodeHolder<String>>();
		order.addLast(tree);
		
		order.addLast(layer1node1);
		order.addLast(layer1node2);
		
		order.addLast(layer2node1);
		order.addLast(layer2node2);
		
		order.addLast(layer2node3);
		order.addLast(layer2node4);
		
		testIterator(tree.iteratorBreadthFirtst(), order);
	}
	
	private void testIterator(Iterator<DefaultNodeHolder<String>> itr, List<DefaultNodeHolder<String>> expectedOrder){
		Iterator<DefaultNodeHolder<String>> expectedItr = expectedOrder.iterator();
		int count = 0;
		while(itr.hasNext()){
			count++;
			DefaultNodeHolder<String> obj = itr.next();
			DefaultNodeHolder<String> expectedObj = expectedItr.next();
			
			assertEquals(expectedObj, obj);
		}
		assertEquals(expectedOrder.size(), count);
	}
	
	@Test
	public void testRemoveNode(){
		assertTrue(layer1node1.removeChild(layer2node1));
		assertEquals(6, tree.size());
		assertNull(layer2node1.getParent());
	}
	
	@Test
	public void testRemoveNodeSize(){
		assertTrue(layer1node1.removeChild(layer2node1));
		assertEquals(1, layer2node1.size());
	}
	
	@Test
	public void testRemoveSubTree(){
		assertTrue(tree.removeChild(layer1node1));
		assertEquals(4, tree.size());
		assertNull(layer1node1.getParent());
	}
	
	@Test
	public void testRemoveSubTreeSize(){
		assertTrue(tree.removeChild(layer1node1));
		assertEquals(3, layer1node1.size());
	}
	
	@Test
	public void testIterateOutOfBreadthFirstIterator(){
		Iterator<DefaultNodeHolder<String>> itr = tree.iteratorBreadthFirtst();
		while(itr.hasNext()){
			itr.next();
		}
		try{
			itr.next();
			//Should throw an Exception!
			assertTrue(false); 
		}catch(Exception e){
			assertTrue(NoSuchElementException.class.isInstance(e));
		}
	}
	
	public void testIterateOutOfDepthFirstIterator(){
		Iterator<DefaultNodeHolder<String>> itr = tree.iteratorDepthFirtst(TraversationalOrder.PreOrder);
		while(itr.hasNext()){
			itr.next();
		}
		try{
			itr.next();
			//Should throw an Exception!
			assertTrue(false); 
		}catch(Exception e){
			assertTrue(NoSuchElementException.class.isInstance(e));
		}
	}
}
