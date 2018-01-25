package chapter18;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Redirecting {

	public static void main(String[] args) throws IOException {
		PrintStream console = System.out;
		BufferedInputStream in = new BufferedInputStream(
				new FileInputStream(new File("/Users/zhanghui/testFileFolder/s.in")));
		PrintStream out = new PrintStream(
				new BufferedOutputStream(new FileOutputStream(new File("/Users/zhanghui/testFileFolder/s.out"))));
		System.setIn(in);
		System.setOut(out);
		System.setErr(out);

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s = bf.readLine()) != null) {
			System.out.println(s);
		}

		// close StandardOut, reset System.out to default console.
		out.close();
		System.setOut(console);
	}

}
