package chapter05_initialization;

public class AutoBoxingAndVarargs {
	static void f(int... args) {
		System.out.println("int varargs:");
		for (int i : args) {
			System.out.println(i);
		}
	}

	static void g(Integer... args) {
		System.out.println("Integer varargs:");
		for (Integer i : args) {
			System.out.println(i);
		}
	}

	public static void main(String[] args) {
		f();
		g();
		f(1, 2, 2, 3);
		g(1, 2, 3);
		f(new int[] { 1, 2, 7, 5 });
		g(new Integer[] { 1, 2, 3 });

	}

}
