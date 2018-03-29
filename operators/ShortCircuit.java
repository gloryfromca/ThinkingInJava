//:operator/ShortCircuit.java
package operators;

public class ShortCircuit {
	static boolean test3(int val) {
		System.out.println("comparison of val and 3!");
		return val > 3;
	}

	public static void main(String[] args) {
		int s = 1;
		int d = 5;
		System.out.println(test3(s) && test3(d));

	}
}/*
	 * Output:
	 * 
	 * comparison of value and 3!
	 * 
	 * false
	 * 
	 */// :~
