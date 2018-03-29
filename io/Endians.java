package io;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class Endians {

	public static void main(String[] args) {
		ByteBuffer bbf = ByteBuffer.allocate(10);
		bbf.asCharBuffer().put("abcde");
		System.out.println(Arrays.toString(bbf.array()));
		bbf.rewind();
		bbf.order(ByteOrder.BIG_ENDIAN);
		bbf.asCharBuffer().put("abcde");
		System.out.println(Arrays.toString(bbf.array()));
		bbf.rewind();
		bbf.order(ByteOrder.LITTLE_ENDIAN);
		bbf.asCharBuffer().put("abcde");
		System.out.println(Arrays.toString(bbf.array()));
		
	}

}
