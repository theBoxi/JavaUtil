package ch.boxi.javaUtil.id.decorator.format;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Random;
import java.util.TreeMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ch.boxi.javaUtil.id.ID;
import ch.boxi.javaUtil.id.decorator.format.IDFormat;
import ch.boxi.javaUtil.id.decorator.format.SimpleIDFormat;
import ch.boxi.javaUtil.map.SimpleMapEntry;

public class SimpleFormatMultiThreadTest {
	
	private List<SimpleFormatRunner> runners = new LinkedList<SimpleFormatRunner>();
	private IDFormat format = new SimpleIDFormat("##########");
	private boolean errorFound = false;
	
	@Before
	public void init(){
		for(int n = 0; n < 100; n++){
			Map<ID, String> idToExpected = new TreeMap<ID, String>();
			for(int i = 0; i < 100; i++){
				Entry<ID, String> entry = createID();
				idToExpected.put(entry.getKey(), entry.getValue());
			}
			SimpleFormatRunner runner = new SimpleFormatRunner(format, idToExpected, this);
			runners.add(runner);
		}
	}
	
	@Test
	public void RunThreads(){
		ExecutorService executorService = Executors.newFixedThreadPool(100);
		for(SimpleFormatRunner runner: runners){
			executorService.execute(runner);
		}
		Assert.assertFalse("Error Found", errorFound);
	}
	
	public Entry<ID, String> createID(){
		Random random = new Random(System.currentTimeMillis());
		Long longID = random.nextLong();
		String value = longID.toString().substring(0, 10);
		SimpleID id = new SimpleID(Long.parseLong(value));
		SimpleMapEntry<ID, String> entry = new SimpleMapEntry<ID, String>(id, value);
		return entry;
	}
	
	public synchronized void errorFound(){
		errorFound = true;
	}
}