package chapter20_annotations;

import net.mindview.atunit.Test;
import net.mindview.util.OSExecute;

public class AtUnitExample1 {

	public String methodOne() {
		return "methodOne";
	}

	public int methodTwo() {
		return 34;
	}

	@Test
	boolean methodOneTest() {
		return methodOne().equals("methodOne");
	}

	@Test
	boolean methodTwoTest() {
		return methodTwo() == 34;
	}

	@Test
	boolean trueTest() {
		return true;
	}

	@Test
	boolean falseTest() {
		return false;
	}

	public static void main(String[] args) {
		// OSExecute.command("java net.mindview.atunit.AtUnit AtUnitExample1");
		System.out.println("in AtUnitExample1!");
	}

}
