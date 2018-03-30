package chapter03_operators;

public class URShift {

	public static void main(String[] args) {
		short s = -1;
		System.out.println(s >>>= 10);
		short s1 = -1;
		s1 >>>= 10;
		System.out.println(s1);
		short s2 = -1;
		long s2Shift = s2 >>> 10;
		System.out.println(s2Shift);
		System.out.println(s2);
		byte b = 127;
		System.out.println(b >> 2);
		System.out.println(b >> 34);
		byte b1 = -1;
		System.out.println(Integer.toBinaryString(b1));
		System.out.println(Integer.toBinaryString(b1 >>> 10));
		System.out.println(b1 >>> 10);// 4194303
		System.out.println(Integer.toBinaryString(b1 >>> 42));
		byte b2 = -1;
		System.out.println(Integer.toBinaryString(b2));
		b2 >>>= 7;
		// will return a strong value.
		System.out.println(Integer.toBinaryString(b2));
		byte b3 = -3;
		System.out.println(Integer.toBinaryString(b3));
		b3 >>>= 7;
		// will return a strong value.
		System.out.println(Integer.toBinaryString(b3));
		byte b4 = -10;
		System.out.println(Integer.toBinaryString(b4));
		b4 >>>= 2;
		// will return a strong value.
		System.out.println(Integer.toBinaryString(b4));
		char c = (char) 56;
		System.out.println(Integer.toBinaryString(c));
		System.out.println(Integer.toBinaryString(c >> 3));
		System.out.println(Integer.toBinaryString(c >> 35));

	}

}/*
	 * Output:
	 */// :~
