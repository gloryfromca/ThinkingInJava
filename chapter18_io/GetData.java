package chapter18_io;

import java.nio.ByteBuffer;

public class GetData {

	public static void main(String[] args) {
		ByteBuffer bbf = ByteBuffer.allocate(1024);
		while (bbf.get() != 0) {
			System.out.println("nonzero");
		}
		System.out.println(bbf);
		bbf.rewind();
		System.out.println(bbf);
		bbf.asCharBuffer().put("zha");
		bbf.rewind();
		char c;
		while ((c = bbf.getChar()) != 0) {
			System.out.print(c + " ");
		}
		System.out.println();
		System.out.println(bbf);
		bbf.rewind();
		bbf.asShortBuffer().put((short) 471142);
		bbf.rewind();
		System.out.println(bbf.getShort());
		bbf.rewind();
		bbf.asIntBuffer().put(99471142);
		System.out.println(bbf.getInt());
		bbf.rewind();
		bbf.asLongBuffer().put(99471142);
		System.out.println(bbf.getLong());
		bbf.rewind();
		bbf.asDoubleBuffer().put(99471142);
		System.out.println(bbf.getDouble());

	}

}
