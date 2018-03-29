package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class UsagesOfBytesBuffer {
	final static int CAPACITY = 1024;

	public static void main(String[] args) throws IOException {
		FileChannel fisc = new FileOutputStream(new File("/home/zhanghui/BasicFileOutput")).getChannel();
		fisc.write(ByteBuffer.wrap("some text".getBytes()));
		fisc.close();
		fisc = new FileInputStream(new File("/home/zhanghui/BasicFileOutput")).getChannel();
		ByteBuffer result = ByteBuffer.allocate(CAPACITY);

		fisc.read(result);
		// read BytesBuffer repeatedly.
		result.flip();
		while (result.hasRemaining()) {
			System.out.print((char) result.get());
		}
		System.out.println("\n========================");
		result.flip();
		while (result.hasRemaining()) {
			System.out.print((char) result.get());
		}
		System.out.println("\n========================");

		// change the channel's file position.
		System.out.println("fisc's position: " + fisc.position());
		// return BytesBuffer's position.
		System.out.println("result's position: " + result.position());
		// result's limit is set to 9 , it's not capacity until you clear().
		fisc.read(result);
		// set fisc's position to 0, you can read bytes from the begin of fisc's file.
		fisc.position(0);
		result.position(3);
		// it works. will reads bytes from position 0 of fisc's file,
		// writes bytes from result's position 3, ends by result's limit 9.
		fisc.read(result);
		result.flip();
		while (result.hasRemaining()) {
			System.out.print((char) result.get());
		}
		System.out.println("\n========================");

		fisc.position(0);
		result.position(0);
		System.out.println(result);
		result.compact();
		System.out.println(result);
		fisc.read(result);
		System.out.println(result);
		result.flip();
		System.out.println(result);
		while (result.hasRemaining()) {
			System.out.print((char) result.get());
		}

	}

}
