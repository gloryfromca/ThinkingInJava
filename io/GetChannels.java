package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.*;

public class GetChannels {

	public static void main(String[] args) throws IOException {
		String pathname = "/home/zhanghui/BasicFileOutput";
		FileChannel fc = new FileOutputStream(new File(pathname)).getChannel();
		ByteBuffer bbf = ByteBuffer.allocateDirect(1024);
		System.out.println(bbf);
		// it's a bad code , new buffer with "zhanghui".length will be assigned to bbf.
		// bbf = bbf.wrap("zhanghui".getBytes());

		fc.write(ByteBuffer.wrap("zhanghui".getBytes()));
		fc.close();

		fc = new RandomAccessFile(new File(pathname), "rw").getChannel();
		// must relocate to the end of fc, or will cause overwrite.
		fc.position(fc.size());
		// bbf = bbf.wrap(" some more".getBytes());
		fc.write(ByteBuffer.wrap(" some more".getBytes()));
		System.out.println(fc.size());
		fc.close();

		fc = new FileInputStream(new File(pathname)).getChannel();
		fc.read(bbf);
		bbf.flip();
		while (bbf.hasRemaining()) {
			System.out.print((char) bbf.get());
		}
		System.out.println();

	}

}
