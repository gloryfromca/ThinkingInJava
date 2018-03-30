package chapter17_containers;

import java.util.ArrayList;
import java.util.Iterator;

public class FailFast {

	public static void main(String[] args) {
		ArrayList<String> al = new ArrayList<String>();
		al.add("zhang");
		al.add("hui");
		Iterator<String> alIter = al.iterator();
		// java.util.ConcurrentModificationException
		// al.add("john");
		while (alIter.hasNext()) {
			System.out.println(alIter.next());
		}

	}

}
