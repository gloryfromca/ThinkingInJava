package generics;

import java.lang.reflect.Array;

public class ArrayOfGenericWithTypeToken<T> {
	// you can't new T[], but you can state T[] variables.
	private T[] ts;

	public ArrayOfGenericWithTypeToken(Class<T> type, int size) {
		// ts is already T[] in run-time.
		ts = (T[]) Array.newInstance(type, size);
	}

	public void put(int index, T item) {
		ts[index] = item;
	}

	public T get(int index) {
		return ts[index];
	}

	public T[] rep() {
		return ts;
	}

	public static void main(String[] args) {
		ArrayOfGenericWithTypeToken<String> agtk = new ArrayOfGenericWithTypeToken<>(String.class, 5);
		agtk.put(0, "first");
		String string = agtk.get(0);
		System.out.println(string);

		String[] stringArray = agtk.rep();
		for (String string1 : stringArray) {
			System.out.println(string1);
		}
	}

}
