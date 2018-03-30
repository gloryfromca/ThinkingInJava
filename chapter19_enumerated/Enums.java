package chapter19_enumerated;

import java.util.Random;

public class Enums {
	private static Random random = new Random();

	public static <T extends Enum<T>> T random(Class<T> ec) {
		return random(ec.getEnumConstants());
	}

	public static <T> T random(T[] enums) {
		return enums[random.nextInt(enums.length)];
	}
}
