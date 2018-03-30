package chapter09_interfaces;

import java.util.Random;

interface Constants {
	// all fields in interface are static and final.
	int r1 = new Random().nextInt();
	int r2 = new Random().nextInt();

}

class A implements Constants {

}

class B implements Constants {

}

public class InterfaceStaticFinalField {

	public static void main(String[] args) {
		System.out.println("A's fields are: " + A.r1);
		System.out.println("B's fields are: " + B.r1);
	}

}
