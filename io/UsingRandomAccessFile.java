package io;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class UsingRandomAccessFile {

	public static void display(RandomAccessFile raf) throws IOException {
		// relocate to the begin of RandomAccessFile, then display.
		raf.seek(0);
		for (int i = 0; i < 7; i++) {
			System.out.println(raf.readLong());
		}

	}

	public static void main(String[] args) throws IOException {
		RandomAccessFile raf = new RandomAccessFile(new File("/home/zhanghui/raf.dat"), "rw");
		for (int i = 0; i < 7; i++) {
			raf.writeLong(1L);
		}
		display(raf);

		System.out.println("=============");
		// long is 8 bytes.
		// seek(16) is the location of the first byte of Long type.
		raf.seek(16);
		raf.writeLong(9L);
		display(raf);

		// you should close file after display().
		raf.close();

	}

}
