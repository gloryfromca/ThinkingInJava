package holding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class ReversibleArrayList<T> extends ArrayList<T> {
	// derived-class can't use base-class' constructor with params directly,
	// you must write a constructor with params in base-class.
	public ReversibleArrayList(Collection<T> c) {
		super(c);
	}

	public Iterable<T> reversed() {
		return new Iterable<T>() {
			public Iterator<T> iterator() {
				return new Iterator<T>() {
					int current = size() - 1;

					@Override
					public boolean hasNext() {
						return current >= 0;

					}

					@Override
					public T next() {
						return get(current--);

					}

				};

			}
		};
	}

	public static void main(String[] args) {
		ReversibleArrayList<String> rs = new ReversibleArrayList<String>(
				Arrays.asList("to be or not to be".split(" ")));
		for (String s : rs) {
			System.out.print(s + " ");
		}
		System.out.println();
		for (String s : rs.reversed()) {
			System.out.print(s + " ");
		}

	}

}
