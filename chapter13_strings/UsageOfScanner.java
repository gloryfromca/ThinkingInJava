package chapter13_strings;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.Scanner;


public class UsageOfScanner {
	static BufferedReader input = new BufferedReader(new StringReader("zhanghui"+
		"\n 22 15.2"));
	public static void main(String[] args) {
		Scanner scanner= new Scanner(input);
		String name = scanner.nextLine();
		int age = scanner.nextInt();
		double favorite = scanner.nextDouble();
		System.out.println("name: "+name);
		System.out.println("age: "+ age);
		System.out.println("favorite double: "+ favorite);
		
		
		
	}

}
