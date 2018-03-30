//:operators/ExponentsAndLiterals.java
package chapter03_operators;

public class ExponentsAndLiterals {

	public static void main(String[] args) {
		int h = 0x1EF;
		System.out.println(h);
		System.out.println(Integer.toBinaryString(h));
		int o = 0166;
		System.out.println(o);
		System.out.println(Integer.toBinaryString(o));
		char c = 0x57;
		System.out.println(c);
		System.out.println(Integer.toBinaryString(c));
		char c1 = 0xffff;
		System.out.println(c1);
		System.out.println(Integer.toBinaryString(c1));
		byte b = 0x7f;
		System.out.println(b);
		System.out.println(Integer.toBinaryString(b));
		// cannot convert from int to byte
		// byte s = 0xeff;
		short s = 0xff;
		System.out.println(s);
		System.out.println(Integer.toBinaryString(s));
		// cannot convert from int to short
		// short s= 0xffff;
		byte b1 = (byte) 1.1F;
		System.out.println(Integer.toBinaryString(b1));
		float f = 10.234F;
		System.out.println(f);
		float f1 = 0.435645612232132F;
		System.out.println(Float.toString(f1));
		double d = (double) 1F;
		System.out.println(d);
		//e is 10, not 2.718
		double d1 = (double)47e23F;
		System.out.println(d1);

	}

}/*
	*/// :~
