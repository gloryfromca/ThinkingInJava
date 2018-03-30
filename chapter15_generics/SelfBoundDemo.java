package chapter15_generics;

import com.sun.media.jfxmedia.events.NewFrameEvent;

class SelfBound<T extends SelfBound<T>> {
	T c;

	public void set(T c) {
		this.c = c;
	}

	public T get() {
		return c;
	}
}

class A extends SelfBound<A> {

}

class B extends SelfBound<A> {

}

class C {

}

// The type C is not a valid substitute for the bounded parameter <T extends
// SelfBound<T>> of the type SelfBound<T>
// class D extends SelfBound<C> {}

class D extends SelfBound {

}

// The type C is not a valid substitute for the bounded parameter <T extends
// SelfBound<T>> of the type SelfBound<T>
// class E extends SelfBound<D> {}

public class SelfBoundDemo {

	public static void main(String[] args) {
		A a = new A();
		a.set(new A());
		System.out.println(a.get());
		
	}

}
