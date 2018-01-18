package chapter15;

class NotSelfBound<T> {
	T c;

	public void set(T c) {
		this.c = c;
	}

	public T get() {
		return c;
	}
}

class A1 extends NotSelfBound<A1> {

}

class D1 {

}

class D2 extends NotSelfBound<D1> {

}

public class NotSelfBoundDemo {

	public static void main(String[] args) {
		A1 a1 = new A1();
		a1.set(new A1());
		System.out.println(a1.get());

	}

}
