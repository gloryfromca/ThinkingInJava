package containers;

import java.util.BitSet;
import java.util.Random;

public class Bits {
	final static Random RANDOM = new Random(47);

	public static void main(String[] args) {
		BitSet bt = new BitSet();
		System.out.println(bt);
		byte b = (byte) RANDOM.nextInt();
		System.out.println(Integer.toBinaryString((int) b));
		for (int i = 1; i < 10; i++) {
			System.out.println(Integer.toBinaryString((int) (1 << i)));
			if (((1 << i) & b) != 0) {
				bt.set(i);
			} else {
				bt.clear(i);
			}
		}
		System.out.println(bt);
	}

}
