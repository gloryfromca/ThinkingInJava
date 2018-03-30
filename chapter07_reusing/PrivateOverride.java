package chapter07_reusing;

public class PrivateOverride {
	private void f() {
		System.out.println("father");
	}

	public static void main(String[] args) {		
		// 1 private-method is final-method. 
		// 2 you can run these code because main() are in base-class,
		//   if you put main() into Son, 
		//   you can't access private f() belonged to base-class.Ï
		PrivateOverride po = new PrivateOverride();
		po.f();
		PrivateOverride son = new Son();
		son.f();
		
		// code below will be OK whether you put it in base-class or 
		// derived-class.
		// because Son has own f() method, compiler will see its f() method
		// as a new method not contained in base-class.
		Son son1 = new Son();
		son1.f();
	}

}

class Son extends PrivateOverride {
	public void f() {
		System.out.println("son");
	}

}
