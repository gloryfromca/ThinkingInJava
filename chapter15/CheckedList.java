package chapter15;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Cat {

}

class Dog {

}

public class CheckedList {

	@SuppressWarnings("unchecked")
	static void oldStyleMethods(List dogs) {
		System.out.println("will add");
		dogs.add(new Cat());
		System.out.println("addition succeed");
	}

	public static void main(String[] args) {
		List<Dog> dogs = new ArrayList<Dog>();
		oldStyleMethods(dogs);
		try {
			// will not have compile-error.
			Dog dog = dogs.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<Dog> anotherDogs = Collections.checkedList(new ArrayList<Dog>(), Dog.class);
		oldStyleMethods(anotherDogs);
		Dog dog1 = anotherDogs.get(0);

	}

}
