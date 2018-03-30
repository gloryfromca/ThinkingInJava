package chapter10_innerclasses;

interface Selector {
	boolean end();

	void next();

	Object current();
}

class Sequence {
	private int count;
	private Object[] s;

	public Sequence(int n) {
		s = new Object[n];
	}

	public void add(Object item) {
		if (count < s.length) {
			s[count++] = item;
		} else {
			System.err.println("Sequence is full!");
		}
	}

	private class SequenceSelector implements Selector {
		int i;

		@Override
		public boolean end() {
			if (i >= count)
				return true;
			return false;
		}

		@Override
		public void next() {
			if (i < count) {
				i++;
			} else {
				System.out.println("next has nothing!");
			}
		}

		@Override
		public Object current() {
			return s[i];
		}

	}

	public Selector selector() {
		return new SequenceSelector();
	}

}

public class IteratorPattern {

	public static void main(String[] args) {
		Sequence s = new Sequence(10);
		for (int j = 0; j < 5; j++) {
			s.add(Integer.toString(j));
		}
		Selector ss = s.selector();
		while (!ss.end()) {
			System.out.println(ss.current());
			ss.next();
		}
	}

}
