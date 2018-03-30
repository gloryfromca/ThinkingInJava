package chapter15_generics;

class Base {

}

class Decorator extends Base {
	private Base base;

	public Decorator(Base base) {
		this.base = base;
	}

	public Base get() {
		return base;
	}

	public void set(Base base) {
		this.base = base;
	}

	public void doSomething() {
		System.out.println(base + " is doing something!");
	}
}

class AdvancedDecorator extends Decorator {

	public AdvancedDecorator(Base base) {
		super(base);

	}

	public void advancedMethod() {
		System.out.println(this + "'s advancedMethod is being done!");
	}

}

class AnotherAdvancedDecorator extends Decorator {

	public AnotherAdvancedDecorator(Base base) {
		super(base);

	}

	public void anotherAdvancedMethod() {
		System.out.println(this + "'s anotherAdvancedMethod method is being done!");
	}

}

public class DecorationPattern {

	public static void main(String[] args) {
		AdvancedDecorator ad = new AdvancedDecorator(new Base());
		ad.get();
		ad.set(new Base());
		ad.advancedMethod();

		AdvancedDecorator ad1 = new AdvancedDecorator(new AnotherAdvancedDecorator(new Base()));
		ad1.get();
		ad1.set(new Decorator(new Base()));
		ad1.advancedMethod();

		// anotherAdvancedMethod() belongs to `AnotherAdvancedDecorator` which instance
		// is regarded as parameter in the procedure of instantiating
		// `AdvancedDecorator` that base-class `Decorator` will deputize the parameter
		// to make anotherAdvancedMethod() disappear.
		// ad1.anotherAdvancedMethod();
	}

}
