package io;

import java.io.*;
import java.util.regex.Pattern;

class MyFilter implements FilenameFilter {

	private Pattern pattern;

	public MyFilter(String regex) {
		pattern = Pattern.compile(regex);

	}

	@Override
	public boolean accept(File dir, String name) {
		return pattern.matcher(name).matches();
	}

}

public class DirList {

	public static void main(String[] args) {
		File file = new File("/home/zhanghui");
		for (String f : file.list()) {
			System.out.println(f);
		}
		System.out.println("==========================");
		for (String f : file.list(new MyFilter("[^.].*"))) {
			System.out.println(f);
		}
	}

}
