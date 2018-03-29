package containers;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class MapEntry<K, V> implements Map.Entry<K, V> {
	private K key;
	private V value;

	public MapEntry(K key, V value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public K getKey() {
		return key;
	}

	@Override
	public V getValue() {
		return value;
	}

	@Override
	public V setValue(V value) {
		V oldValue = this.value;
		this.value = value;
		return oldValue;
	}

	@Override
	public String toString() {
		return key + "=" + value;
	}

}

public class SimpleHashMap<K, V> extends AbstractMap<K, V> {
	private final static int SIZE = 977;

	@SuppressWarnings("unchecked")
	private ArrayList<MapEntry<K, V>>[] buckets = new ArrayList[SIZE];

	@Override
	public Set<Entry<K, V>> entrySet() {
		// you can new HashSet<Map.Entry<K, V>> (it can be generic)
		Set<Map.Entry<K, V>> results = new HashSet<Map.Entry<K, V>>();
		for (ArrayList<MapEntry<K, V>> bucket : buckets) {
			if (bucket == null)
				continue;
			// if bucket is null. code below will raise Exception.
			for (MapEntry<K, V> entry : bucket) {
				results.add(entry);
			}
		}
		return results;
	}

	// type of key is Object
	public V get(Object key) {
		int index = key.hashCode() % SIZE;
		ArrayList<MapEntry<K, V>> bucket = buckets[index];

		if (buckets[index] != null) {
			for (MapEntry<K, V> entry : bucket) {
				if (entry.getKey().equals(key)) {
					return entry.getValue();
				}
			}
		}
		return null;
	}

	public V put(K key, V value) {
		int index = key.hashCode() % SIZE;
		if (buckets[index] == null)
			buckets[index] = new ArrayList<MapEntry<K, V>>();
		boolean found = false;
		V oldValue = null;
		ArrayList<MapEntry<K, V>> bucket = buckets[index];
		for (Entry<K, V> entry : bucket) {
			if (entry.getKey().equals(key)) {
				found = true;
				entry.setValue(value);
				break;
			}
		}
		if (!found) {
			buckets[index].add(new MapEntry<K, V>(key, value));
		}
		return oldValue;
	}

	public static void main(String[] args) {
		System.out.println(new Integer(1).hashCode());
		System.out.println(new Integer(978).hashCode());

		SimpleHashMap<Integer, String> shm = new SimpleHashMap<Integer, String>();
		shm.put(1, "zhang");
		shm.put(2, "hui");
		shm.put(3, "jiang");
		shm.put(4, "saiya");
		shm.put(978, "nan");

		// Unlikely argument type String for get(Object) on a Map<Integer,String>
		System.out.println(shm.get("1"));
		System.out.println(shm.get(1));
		System.out.println(shm.get(2));
		System.out.println(shm.get(978));

		System.out.println(shm);

	}

}
