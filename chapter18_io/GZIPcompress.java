package chapter18_io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GZIPcompress {

	public static void main(String[] args) throws IOException {
		System.out.println("zhanghui".getBytes().length);
		// BufferedReader ==> buffering character-input stream
		BufferedReader in = new BufferedReader(new StringReader("zhanghui\nzaici"));
		BufferedOutputStream out = new BufferedOutputStream(
				new GZIPOutputStream(new FileOutputStream(FilesForTest.outMac)));

		int c;
		while ((c = in.read()) != -1) {
			out.write(c);
		}
		out.close();
		in.close();
		BufferedReader gzipIn = new BufferedReader(
				new InputStreamReader(new GZIPInputStream(new FileInputStream(FilesForTest.outMac))));
		String s;
		while ((s = gzipIn.readLine()) != null) {
			System.out.println(s);
		}
	}

}
