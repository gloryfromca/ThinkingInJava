//:operator:AssignmentWithObject.java
// Assignment with object is a bit tricky.
package chapter3;

class Car {
	int level;
	String name;

	public Car(String name) {
		this.name = name;
	}
}

public class AssignmentWithObject {

	public static void main(String[] args) {
		Car c1 = new Car("A");
		Car c2 = new Car("B");
		System.out.println(c1.name + "'s level:" + c1.level);
		System.out.println(c2.name + "'s level:" + c2.level);
		c1.level = 1;
		c2.level = 2;
		System.out.println(c1.name + "'s level:" + c1.level);
		System.out.println(c2.name + "'s level:" + c2.level);
		c1 = c2;
		System.out.println(c1.name + "'s level:" + c1.level);
		System.out.println(c2.name + "'s level:" + c2.level);
		c2.level = 10;
		System.out.println(c1.name + "'s level:" + c1.level);
		System.out.println(c2.name + "'s level:" + c2.level);

	}

}/*Output:
A's level:0
B's level:0
A's level:1
B's level:2
B's level:2
B's level:2
B's level:10
B's level:10
	*/// :~
