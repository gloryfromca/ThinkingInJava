package chapter18_io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StandardInput {

	public static void main(String[] args) throws IOException {
		BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s = bi.readLine()) != null) {
			System.out.println(s);
		}

	}

}
