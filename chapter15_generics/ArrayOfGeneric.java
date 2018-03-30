package chapter15_generics;

public class ArrayOfGeneric<T> {
	private Object[] objects;

	// constructor name doesn't need <T>.
	public ArrayOfGeneric(int size) {
		objects = new Object[size];
	}

	public void put(int index, T item) {
		objects[index] = item;
	}

	@SuppressWarnings("unchecked")
	public T get(int index) {
		return (T) objects[index];
	}

	public T[] rep() {
		return (T[]) objects;
	}

	public static void main(String[] args) {
		ArrayOfGeneric<String> ag = new ArrayOfGeneric<String>(5);
		ag.put(0, "first");
		ag.put(2, "second");
		System.out.println(ag.get(0));
		System.out.println(ag.rep());

		// raise error: cannot be cast to [Ljava.lang.String
		// for (String s : ag.rep()) {
		// System.out.println(s);
		// }

		for (Object s : ag.rep()) {
			System.out.println(s);
		}
		
	}

}
