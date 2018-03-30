package chapter10_innerclasses;

interface CanFly {
	void fly();
}

interface CanRun {
	void run();
}

interface Bird extends CanFly, CanRun {

}

interface InterfaceBirdFactory {
	Bird getABird();
}

class Chicken2 implements Bird {

	String name = "chicken";

	@Override
	public void run() {
		System.out.println(name + " can run 2m/s");
	}

	@Override
	public void fly() {
		System.out.println(name + " can fly low");
	}

	public static InterfaceBirdFactory chickenFactory() {
		return new InterfaceBirdFactory() {
			@Override
			public Bird getABird() {
				return new Chicken2();
			}

		};
	}
}

class Columba2 implements Bird {

	String name = "columba";

	@Override
	public void run() {
		System.out.println(name + " can run 1m/s");
	}

	@Override
	public void fly() {
		System.out.println(name + " can fly high");
	}

	public static InterfaceBirdFactory chickenFactory() {
		return new InterfaceBirdFactory() {
			@Override
			public Bird getABird() {
				return new Columba2();
			}

		};
	}

}

public class FactoryPattern2 {
	public static void Consumer(InterfaceBirdFactory birdFactory) {
		Bird bird = birdFactory.getABird();
		bird.fly();
		bird.run();
	}

	public static void main(String[] args) {
		Consumer(Chicken2.chickenFactory());
		Consumer(Columba2.chickenFactory());

	}

}
