package chapter15;

import java.nio.channels.ScatteringByteChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


interface Combiner<R, T> {
	R work(R r, T t);
}

interface SimpleCombiner<T> extends Combiner<T, T> {
}

interface Test<T> {
	boolean work(T t);
}

interface Transfrom<R, T> {
	R work(T t);
}

interface SimpleTransform<T> extends Transfrom<T, T> {
	T work(T t);
}

interface Collector<T> extends SimpleTransform<T> {

}

class IntegerAdder implements SimpleCombiner<Integer> {

	@Override
	public Integer work(Integer r, Integer t) {
		return r + t;
	}

}

class StringTransform<String> implements Collector<String> {
	int l;

	@SuppressWarnings("unchecked")
	@Override
	public String work(String t) {
		l++;
		return (String) (t + " " + l);
	}

	public int count() {
		return l;
	}

}

class GreaterThan<T extends Comparable<T>> implements Test<T> {

	T s;

	public GreaterThan(T s) {
		this.s = s;
	}

	@Override
	public boolean work(T t) {
		if (t.compareTo(s) > 0)
			return true;
		return false;
	}

}

public class MapReduce {
	static class Functional {
		public static <T> T simpleReduce(Iterable<T> s, SimpleCombiner<T> c) {
			Iterator<T> st = s.iterator();
			T result;
			if (st.hasNext()) {
				result = st.next();
				while (st.hasNext()) {
					result = c.work(result, st.next());
				}
				return result;
			}
			return null;
		}

		public static <T extends Comparable<T>> List<T> compFilter(Iterable<T> s, Test<T> t) {
			List<T> l = new ArrayList<T>();
			for (T i : s) {
				if (t.work(i)) {
					l.add(i);
				}
			}
			return l;

		}

		public static <T> Collector<T> Foreach(Iterable<T> l, Collector<T> c) {
			for (T i : l) {
				c.work(i);
			}
			return c;
		}

		public static <T> List<T> simpleMap(Iterable<T> l, Collector<T> c) {
			List<T> results = new ArrayList<T>();
			for (T i : l) {
				results.add(c.work(i));
			}
			return results;
		}

		public static <T> List<T> filter(List<T> list, Test<T> t) {
			return null;

		}
	}

	public static void main(String[] args) {
		List<Integer> l = Arrays.asList(1, 4, 2, 3, 3);

		Integer result = Functional.simpleReduce(l, new IntegerAdder());
		System.out.println(result);
		
		GreaterThan<Integer> gt = new GreaterThan<Integer>(2);
		List<Integer> gtl = Functional.compFilter(l, gt);
		System.out.println(gtl);
		
		List<String> ls = Arrays.asList("I", "love", "you");
		StringTransform<String> collector = new StringTransform();
		List<String> rls = Functional.simpleMap(ls, collector);
		System.out.println(rls);
		
		StringTransform<String> collector1 = (StringTransform<String>) Functional.Foreach(ls, collector);
		System.out.println(collector1.count());

	}

}
