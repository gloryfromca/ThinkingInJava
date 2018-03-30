package chapter18_io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class BasicFileOutput {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(new File("/home/zhanghui/BasicFileOutput.java")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("/home/zhanghui/BasicFileOutput.out")));
		String s;
		int count = 0;
		while ((s = in.readLine()) != null) {
			// you can think PrintWriter's print is append or add.
			out.println(count++ + ": " + s);
		}
		out.close();

	}

}
