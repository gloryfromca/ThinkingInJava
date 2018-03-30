package chapter16_arrays;

public class ArrayAutoboxing {

	public static void main(String[] args) {
		Integer[] integers = { 1, 2, 2, 4, 3 };
		int[] ints1 = { 1, 2, 2, 4, 3 };
		int[] ints2 = { new Integer(1), 2, 2, 4, 3 };
		if (ints2[0] == 1) {
			System.out.println("Integer 1 == int 1");
		}
		ints2 = ints1;

		// can't convert int[] to Integer[]
		// integers = ints2;
		// ints2 = integers;

	}

}
