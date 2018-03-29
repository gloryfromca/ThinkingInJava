package containers;

import java.util.HashMap;
import java.util.WeakHashMap;

import com.sun.jndi.url.iiopname.iiopnameURLContextFactory;

class Element {
	int id;

	public Element(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Element && (id == ((Element) obj).id);
	}

	@Override
	public String toString() {
		return Integer.toString(id);
	}

	@Override
	protected void finalize() throws Throwable {
		System.out.println("Finalizing " + id);
	}
}

class Key extends Element {

	public Key(int id) {
		super(id);
	}

}

class Value extends Element {

	public Value(int id) {
		super(id);
	}

}

public class CanonicalMapping {

	public static void main(String[] args) {
		final int SIZE = 10;
		HashMap<Key, Value> map = new HashMap<Key, Value>();
		WeakHashMap<Key, Value> whm = new WeakHashMap<Key, Value>();
		for (int i = 0; i < SIZE; i++) {
			Key key = new Key(i);
			Value value = new Value(i);
			whm.put(key, value);
			if (i % 3 == 0)
				map.put(key, value);
		}
		System.out.println(map);
		System.out.println(whm);
		System.gc();
		System.out.println(map);
		System.out.println(whm);

	}
}
