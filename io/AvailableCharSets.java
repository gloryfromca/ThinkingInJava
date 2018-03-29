package io;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.SortedMap;

public class AvailableCharSets {

	public static void main(String[] args) {
		SortedMap<String, Charset> charsets = Charset.availableCharsets();
		Iterator<String> it = charsets.keySet().iterator();
		String cs;
		while (it.hasNext()) {
			cs = it.next();
			System.out.println(cs + ":");
			Iterator<String> itcs = Charset.forName(cs).aliases().iterator();
			while (itcs.hasNext()) {
				System.out.print(itcs.next() + ", ");
			}
			System.out.println();

		}

	}

}
