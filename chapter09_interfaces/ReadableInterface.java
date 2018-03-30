package chapter09_interfaces;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.Random;
import java.util.Scanner;

public class ReadableInterface implements Readable {

	public static void main(String[] args) {
		Scanner sc = new Scanner(new ReadableInterface(5));
		while (sc.hasNext()) {
			System.out.println(sc.next());
		}

	}

	private int count;
	private Random random = new Random(47);
	private final static char[] capitals = "ABCDE".toCharArray();
	private final static char[] lowers = "abcde".toCharArray();
	private final static char[] vowels = "aeiou".toCharArray();

	public ReadableInterface(int count) {
		this.count = count;
	}

	@Override
	public int read(CharBuffer cb) throws IOException {
		if (count-- == 0)
			return -1;
		cb.append(capitals[random.nextInt(capitals.length)]);
		for (int i = 0; i < 4; i++) {
			cb.append(lowers[random.nextInt(lowers.length)]);
			cb.append(vowels[random.nextInt(vowels.length)]);
		}
		cb.append(" ");
		return 10;

	}

}
