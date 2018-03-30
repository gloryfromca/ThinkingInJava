package chapter10_innerclasses;

class WithInner {
	class Inner {

	}
}

public class InheritInner extends WithInner.Inner {
	public InheritInner(WithInner wi) {
		wi.super();
	}

	public static void main() {
		WithInner wi = new WithInner();
		InheritInner ii = new InheritInner(wi);
	}
}
