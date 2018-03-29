//:operator:EquivalenceAndEqual.java
package operators;

class Value {
	int i;
}

public class EquivalenceAndEqual {

	public static void main(String[] args) {
		int i1 = 10;
		int i2 = 10;
		// comparison of actual value for basic type
		System.out.println("i1==i2:");
		System.out.println(i1 == i2);
		Integer s1 = new Integer(10);
		Integer s2 = new Integer(10);
		// comparison of references
		System.out.println("s1==s2:");
		System.out.println(s1 == s2);
		// comparison of actual value, equal() is overrode to compare values
		System.out.println("s1.equal(s2):");
		System.out.println(s1.equals(s2));
		Value v1 = new Value();
		Value v2 = new Value();
		// comparison of references
		System.out.println("v1==v2:");
		System.out.println(v1 == v2);
		// equal()'s default comparison of references
		System.out.println("v1.equal(v2):");
		System.out.println(v1.equals(v2));

	}

}/*
	 * Output:
	 */// :~
