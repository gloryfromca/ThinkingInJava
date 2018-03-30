package chapter15_generics;

import java.util.ArrayList;
import java.util.List;

interface Fruit {

}

public class UnboundedWildCard {
	static List<?> wildList = new ArrayList();

	// ? represents a specific type that we don't know.
	static List<?> wildList1 = new ArrayList<String>();

	// <? extends Fruit> means everything derived from Fruit,
	// So new ArrayList() can be assigned to it's List
	static List<? extends Fruit> objectList = new ArrayList();
	static List<? extends Fruit> objectList1 = new ArrayList<>();

	static void assign(List<?> list) {
		// something wrong
		// objectList = list;

	};

	public static void main(String[] args) {

	}

}
