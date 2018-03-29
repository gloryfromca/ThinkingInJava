package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class TransferTo {

	public static void main(String[] args) throws IOException {
		String inPathname = "/home/zhanghui/s.in";
		String outPathname = "/home/zhanghui/s.out";
		FileChannel in = new FileInputStream(new File(inPathname)).getChannel();
		FileChannel out = new FileOutputStream(new File(outPathname)).getChannel();
		in.transferTo(0, 14, out);
		in.close();
		out.close();
	}

}
