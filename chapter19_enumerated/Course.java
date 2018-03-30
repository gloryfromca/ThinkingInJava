package chapter19_enumerated;

import chapter19_enumerated.Food;

public enum Course {
	APPETIZER(Food.Appetizer.class), COFFEE(Food.Coffee.class);

	private Food[] values;

	private Course(Class<? extends Food> kind) {
		values = kind.getEnumConstants();
	}

	public Food randomSelection() {
		return Enums.random(values);
	}

	public static void main(String[] args) {
		for (int i = 0; i < 4; i++) {
			for (Course c : Course.values()) {
				Food food = c.randomSelection();
				System.out.println(food);
			}

		}
	}

}
