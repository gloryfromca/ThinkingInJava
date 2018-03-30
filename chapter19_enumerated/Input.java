package chapter19_enumerated;

import java.util.Random;

public enum Input {
	ONE(1), FIVE(5), TWENTY(20), SODA(6), COFFEE(10), WATER(3), ABORT_TRANSACTION {
		public int amount() {
			throw new RuntimeException("ABORT_TRANSACTION.amount()");
		}
	},
	STOP {
		public int amount() {
			throw new RuntimeException("STOP.amount()");
		}
	}

	;
	int value = 0;

	private Input() {

	}

	private Input(int value) {
		this.value = value;
	}

	public int amount() {
		return value;
	}

	static Random rand = new Random();

	public static Input InputSelection() {
		return values()[rand.nextInt(values().length)];
	}

}
