package chapter13_strings;

public class InfiniteRecursion {
	@Override
	public String toString() {
		// `+ this` will call toString() that is what we're writing.
		// return "memory address: " + this;
		return super.toString();
	}

	public static void main(String[] args) {
		System.out.println(new InfiniteRecursion());
	}

}
