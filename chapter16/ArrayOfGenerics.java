package chapter16;

import java.util.ArrayList;
import java.util.List;

public class ArrayOfGenerics<T> {
	public void work() {
		@SuppressWarnings("unchecked")
		T[] ts = (T[]) new Object[5];
		System.out.println(ts);

		@SuppressWarnings("unchecked")
		List<String>[] ls = (List<String>[]) new List[5];

		ls[1] = new ArrayList<String>();
		// ls[1].add(1);
		ls[1].add("s");
		System.out.println(ls);
		System.out.println(ls[1]);

	}

	public static void main(String[] args) {
		new ArrayOfGenerics<String>().work();

	}
}
