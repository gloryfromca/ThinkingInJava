//:initialization/TerminationCondition.java
// Using finalize() to detect an object that hasn't been properly cleaned up.
package initialization;

class Book {
	boolean checkOut = false;

	public Book(boolean checkOut) {
		this.checkOut = checkOut;
	}

	public void CheckIn() {
		this.checkOut = false;
	}

	protected void finalize() {
		if (checkOut) {
			System.err.println("a book isn't check-in!");
		} else {
			System.out.println("a book has already been check-in!");
		}
	}
}

public class TerminationCondition {

	public static void main(String[] args) {
		Book b1 = new Book(true);
		b1.CheckIn();
		System.gc();
		new Book(true);
		System.gc();
		new Book(true).CheckIn();
		System.gc();

	}

}
