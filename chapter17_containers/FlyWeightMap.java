package chapter17_containers;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Set;

import java.util.Map;

public class FlyWeightMap extends AbstractMap<Integer, Character> {
	private int size;
	private static Character[] chars = { 'a', 'b', 'c', 'd' };

	static class Entry implements Map.Entry<Integer, Character> {
		private int index;

		public Entry(int index) {
			this.index = index;
		}

		@Override
		public Integer getKey() {
			return index;
		}

		@Override
		public Character getValue() {
			return chars[index % chars.length];
		}

		@Override
		public Character setValue(Character value) {
			throw new UnsupportedOperationException();
		}

		@Override
		public String toString() {
			return getKey() + "=" + getValue();
		}

	}

	static class EntrySet extends AbstractSet<Map.Entry<Integer, Character>> {
		private int size;

		public EntrySet(int size) {
			this.size = size;
		}

		class Iter implements Iterator<Map.Entry<Integer, Character>> {
			private int size;
			private Entry entry;

			public Iter(int size) {
				this.size = size;
				this.entry = new Entry(-1);
			}

			@Override
			public boolean hasNext() {
				return entry.index < size - 1;
			}

			@Override
			public java.util.Map.Entry<Integer, Character> next() {
				entry.index++;
				return entry;
			}

		}

		@Override
		public Iterator<java.util.Map.Entry<Integer, Character>> iterator() {
			return new Iter(chars.length);
		}

		@Override
		public int size() {
			return size;
		}

	}

	private static Set<Map.Entry<Integer, Character>> entries = new EntrySet(chars.length);

	@Override
	public Set<Map.Entry<Integer, Character>> entrySet() {
		return entries;
	}

	public static void main(String[] args) {
		FlyWeightMap flyWeightMap = new FlyWeightMap();
		System.out.println(flyWeightMap.size());
		System.out.println(flyWeightMap.entrySet());
		for (Map.Entry<Integer, Character> me : flyWeightMap.entrySet()) {
			System.out.println(me);
		}

	}

}
