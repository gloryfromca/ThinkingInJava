package chapter19_enumerated;

import static chapter19_enumerated.Input.ABORT_TRANSACTION;
import static chapter19_enumerated.Input.COFFEE;
import static chapter19_enumerated.Input.FIVE;
import static chapter19_enumerated.Input.ONE;
import static chapter19_enumerated.Input.SODA;
import static chapter19_enumerated.Input.STOP;
import static chapter19_enumerated.Input.TWENTY;
import static chapter19_enumerated.Input.WATER;

import java.util.EnumMap;
import java.util.Iterator;

enum Category {

	MONEY(ONE, FIVE, TWENTY), ITEM_SELECTION(SODA, COFFEE, WATER), QUIT_TRANSACTION(ABORT_TRANSACTION), SHUT_UP(STOP);

	Input[] inputs;

	private Category(Input... inputs) {
		this.inputs = inputs;
	}

	private static EnumMap<Input, Category> categories = new EnumMap<Input, Category>(Input.class);
	static {
		for (Category c : Category.class.getEnumConstants()) {
			for (Input i : c.inputs) {
				categories.put(i, c);
			}
		}
	}

	public static Category categorize(Input input) {
		return categories.get(input);
	}

}

class RandomInputGenerator implements Iterator<Input> {

	@Override
	public boolean hasNext() {
		return true;
	}

	@Override
	public Input next() {
		return Input.InputSelection();
	}
}

public class VendingMachine {
	private static State state = State.WAITING;
	private static int amount = 0;
	private static Input selection = null;

	enum StateDuration {
		TRANSIENT
	}

	enum State {
		WAITING {
			void next(Input input) {
				switch (Category.categorize(input)) {
				case MONEY:
					amount += input.amount();
					state = ADDING_MONEY;
					// break must be in last line of a `case`.
					break;
				case SHUT_UP:
					System.out.println("shut down when WAITING!");
					state = State.TERMINAL;
					break;
				default:
				}
			}
		},
		ADDING_MONEY {
			void next(Input input) {
				switch (Category.categorize(input)) {
				case MONEY:
					amount += input.amount();
					state = ADDING_MONEY;
					break;
				case ITEM_SELECTION:
					if (amount < input.amount()) {
						System.out.println("insufficient money!\nnow:" + amount + ", item:" + input.amount());
						state = ADDING_MONEY;
					} else {
						selection = input;
						state = State.DISPENSING;
					}
					break;
				case QUIT_TRANSACTION:
					System.out.println("I wanna quit transaction!");
					state = GIVING_CHANGE;
					break;
				default:
				}
			}

		},
		DISPENSING(StateDuration.TRANSIENT) {
			void next() {
				System.out.println("here is you selection: " + selection);
				amount -= selection.amount();
				selection = null;
				state = ADDING_MONEY;

			}

		},
		GIVING_CHANGE(StateDuration.TRANSIENT) {
			void next() {
				System.out.println("here is your change: " + amount);
				amount = 0;
				state = WAITING;
			}
		},
		TERMINAL {
			void output() {
				System.out.println("the Machine is shut down!");
			}
		};

		private boolean isTransient = false;

		private State() {
		}

		private State(StateDuration trans) {
			isTransient = true;
		}

		void next(Input input) {
			throw new RuntimeException(this + " don't have next(Input input) func," + " you should override it!");
		}

		void next() {
			throw new RuntimeException(this + " don't have next( ) func," + " you should override it!");
		}

		void amount() {
			System.out.println("you have " + amount + " now");
		}

	}

	static void run(Iterator<Input> iter) {
		while (state != State.TERMINAL) {
			state.next(iter.next());
			while (state.isTransient) {
				state.next();
			}
			state.amount();
		}
	}

	public static void main(String[] args) {
		Iterator<Input> iter = new RandomInputGenerator();
		run(iter);
	}

}
