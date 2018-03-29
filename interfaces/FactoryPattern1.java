package interfaces;

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

class Chicken implements Bird {

	String name = "chicken";

	@Override
	public void run() {
		System.out.println(name + " can run 2m/s");
	}

	@Override
	public void fly() {
		System.out.println(name + " can fly low");
	}

}

class Columba implements Bird {

	String name = "columba";

	@Override
	public void run() {
		System.out.println(name + " can run 1m/s");
	}

	@Override
	public void fly() {
		System.out.println(name + " can fly high");
	}

}

class ColumbaFactory implements InterfaceBirdFactory {

	@Override
	public Bird getABird() {

		return new Columba();
	}

}

class ChickenFactory implements InterfaceBirdFactory {

	@Override
	public Bird getABird() {

		return new Chicken();
	}

}

public class FactoryPattern1 {
	static InterfaceBirdFactory columbaFactory = new ColumbaFactory();
	static InterfaceBirdFactory chickenFactory = new ChickenFactory();

	public static void Consumer(InterfaceBirdFactory birdFactory) {
		Bird bird = birdFactory.getABird();
		bird.fly();
		bird.run();
	}

	public static void main(String[] args) {
		Consumer(new ColumbaFactory());
		Consumer(new ChickenFactory());

	}

}
