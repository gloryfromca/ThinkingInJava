package io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class LargeMappedFiles {
	static int length = 0x8FFFFFF;

	public static void main(String[] args) throws FileNotFoundException, IOException {
		MappedByteBuffer mbbf = new RandomAccessFile(FilesForTest.inMac, "rw").getChannel()
				.map(FileChannel.MapMode.READ_WRITE, 0, length);
		System.out.println(((byte) 'x'));
		// System.out.println((byte)"x");
		System.out.println("x".getBytes().length);
		for (int i = 0; i < length; i++) {
			mbbf.put((byte) 'x');
		}
		for (int i = length / 2; i < length / 2 + 3; i++) {
			System.out.println((char) mbbf.get(i));
		}
	}
}
