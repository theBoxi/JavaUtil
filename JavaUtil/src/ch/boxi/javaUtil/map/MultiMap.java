package ch.boxi.javaUtil.map;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MultiMap<K, V> implements Map<K, List<V>>{
	private Map<K, List<V>>	innerMap	= new HashMap<K, List<V>>();
	
	public void clear() {
		innerMap.clear();
	}
	
	public boolean containsKey(Object arg0) {
		return innerMap.containsKey(arg0);
	}
	
	public boolean containsValue(Object arg0) {
		return innerMap.containsValue(arg0);
	}
	
	public List<V> get(Object arg0) {
		List<V> ret = innerMap.get(arg0);
		if(ret == null){
			ret = new LinkedList<V>();
		}
		return ret;
	}
	
	public boolean isEmpty() {
		return innerMap.isEmpty();
	}
	
	public Set<K> keySet() {
		return innerMap.keySet();
	}
	
	public List<V> remove(Object arg0) {
		return innerMap.remove(arg0);
	}
	
	public void remove(K key, V value) {
		List<V> list = innerMap.get(key);
		if (list != null) {
			list.remove(value);
			if (list.size() == 0) {
				innerMap.remove(key);
			}
		}
	}
	
	public int size() {
		int size = 0;
		for (List<V> list : innerMap.values()) {
			size += list.size();
		}
		return size;
	}

	@Override
	public List<V> put(K key, List<V> arg1) {
		List<V> list = innerMap.get(key);
		for(V elem: arg1){
			if(list == null){
				list = new LinkedList<V>();
				innerMap.put(key, list);
			}
			list.add(elem);
		}
		return list;
	}
	
	public void put(K arg0, V arg1) {
		List<V> existing = innerMap.get(arg0);
		if (existing == null) {
			existing = new LinkedList<V>();
		}
		existing.add(arg1);
		innerMap.put(arg0, existing);
	}

	@Override
	public void putAll(Map<? extends K, ? extends List<V>> arg0) {
		for (K key : arg0.keySet()) {
			put(key,arg0.get(key));
		}
	}

	@Override
	public Collection<List<V>> values() {
		return innerMap.values();
	}
	
	public List<V> allValues(){
		List<V> retList = new LinkedList<V>();
		for(List<V> list: innerMap.values()){
			retList.addAll(list);
		}
		return retList;
	}
	
	@Override
	public Set<java.util.Map.Entry<K, List<V>>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private class MultiMapEntry implements java.util.Map.Entry<K, List<V>>{
		
		private K key;
		private List<V> value;
			
		public MultiMapEntry(K key, List<V> value){
			this.key = key;
			this.value = value;
		}
		
		@Override
		public K getKey() {
			return key;
		}

		@Override
		public List<V> getValue() {
			return value;
		}

		@Override
		public List<V> setValue(List<V> arg0) {
			List<V> oldValue = value;
			value = arg0;
			return oldValue;
		}
		
	}
}
