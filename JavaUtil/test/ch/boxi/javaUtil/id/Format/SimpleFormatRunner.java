package ch.boxi.javaUtil.id.Format;

import java.util.Map;
import java.util.Map.Entry;
import ch.boxi.javaUtil.id.BaseID;

public class SimpleFormatRunner implements Runnable{
	
	private IDFormat format;
	private Map<BaseID, String> idToExpected;
	private SimpleFormatMultiThreadTest test;
	
	public SimpleFormatRunner(IDFormat format, Map<BaseID, String> idToExpected, SimpleFormatMultiThreadTest test){
		this.format = format;
		this.idToExpected = idToExpected;
		this.test = test;
	}
	
	@Override
	public void run() {
		for(Entry<BaseID, String> entry: idToExpected.entrySet()){
			String value = format.formatID(entry.getKey(), "");
			if(!entry.getValue().equals(value)){
				test.errorFound();
			}
		}
	}
}
