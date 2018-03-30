package chapter16_arrays;

class Something {
}

public class DynamicAggregateInitialization {

	public static void main(String[] args) {
		Something[] s0;
		//s0 = new Something[];
		s0 = new Something[] { };
		s0 = new Something[5];
		// s0 = {new Something(), new Something()};
		s0 = new Something[] { new Something(), new Something() };

		Something[] s = new Something[5];
		Something[] s1 = new Something[] { new Something(), new Something() };
		Something[] s2 = { new Something(), new Something() };

	}

}
