package chapter18_io;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class BufferToText {

	public static void main(String[] args) throws IOException {
		File sin = new File(FilesForTest.inMac);
		sin.deleteOnExit();
		sin.createNewFile();

		FileChannel rafc = new RandomAccessFile(sin, "rw").getChannel();
		rafc.write(ByteBuffer.wrap("zhanghui".getBytes()));
		// String.getBytes() will encode String using default charset.
		System.out.println("zhanghui".getBytes().length);
		ByteBuffer bbf = ByteBuffer.allocate(1024);
		// reading rafc should reset the position.
		rafc.position(0);
		rafc.read(bbf);
		// it doesn't work, and current position has changed.
		System.out.println(bbf.asCharBuffer());
		bbf.flip();
		// just get number code of char in utf8.
		while (bbf.hasRemaining()) {
			System.out.println(bbf.get());
		}

		// get current bbf status.
		System.out.println("use default charset to decode:");
		System.out.println(bbf);
		bbf.rewind();
		System.out.println(bbf);
		String encoding = System.getProperty("file.encoding");
		System.out.println(encoding + "\n" + Charset.forName(encoding).decode(bbf));

		System.out.println("write into rafc in UTF-16BE:");
		rafc.position(0);
		System.out.println(rafc.size());
		rafc.write(ByteBuffer.wrap("zhanghuisaiya".getBytes("UTF-16BE")));
		System.out.println(rafc.position());
		bbf.clear();
		// you always need to consider whether there is necessity to reset position.
		rafc.position(0);
		rafc.read(bbf);
		System.out.println(bbf);
		bbf.flip();
		System.out.println(bbf.asCharBuffer());

		System.out.println("use asCharBuffer():");
		rafc.position(0);
		bbf.clear();
		// asCharBuffer will return view of ByteBuffer.
		bbf.asCharBuffer().put("some text more than before");
		rafc.write(bbf);
		bbf.clear();
		rafc.position(0);
		rafc.read(bbf);
		bbf.flip();
		System.out.println(bbf);
		System.out.println(bbf.asCharBuffer());

	}

}
