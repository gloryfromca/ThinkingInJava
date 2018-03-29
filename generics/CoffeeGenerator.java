package generics;

import java.util.Iterator;
import java.util.Random;

interface Generator<T> {
	T next() throws InstantiationException, IllegalAccessException;
}

class Coffee {
	private static int count = 0;
	final private int code = ++count;

	@Override
	public String toString() {

		return getClass().getSimpleName() + " coffee " + code;
	}

}

class JavaCoffee extends Coffee {
}

class JapanCoffee extends Coffee {
}

public class CoffeeGenerator implements Generator<Coffee>, Iterable<Coffee> {

	final private static Random random = new Random();
	private int size = 0;
	private Class[] types = { JapanCoffee.class, JavaCoffee.class, };

	public CoffeeGenerator() {
	}

	public CoffeeGenerator(int size) {
		this.size = size;

	}

	@Override
	public Iterator<Coffee> iterator() {
		return new CoffeeIterator();
	}

	@Override
	public Coffee next() {
		try {

			return (Coffee) types[random.nextInt(types.length)].newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	class CoffeeIterator implements Iterator<Coffee> {

		private int count = size;

		public boolean hasNext() {
			return count > 0;
		}

		public Coffee next() {
			count--;
			return CoffeeGenerator.this.next();
		};
	}

	public static void main(String[] args) {
		CoffeeGenerator gen = new CoffeeGenerator(5);
		for (Coffee c : gen) {
			System.out.println(c);
		}
		for (int i = 0; i < 5; i++) {
			System.out.println(gen.next());
		}
	}

}
