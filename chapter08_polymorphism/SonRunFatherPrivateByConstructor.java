package chapter08_polymorphism;

class Father{
	public Father() {
		f();
		// when JVM calls the base-class' constructor method in 
		//initialization of derived class' instance, late-binding 
		// will work. So g() below will call the Boy class' 
		//overriding method. 
		g();
	}
	private void f() {
		System.out.println("father's private method");
	}
	public void g() {
		System.out.println("father's public method");
	}
}
class Boy extends Father{
	public Boy() {
		f();
		g();
	}
	private void f() {
		System.out.println("boy's private method");
	}
	public void g() {
		System.out.println("boy's public method");
	}
}

public class SonRunFatherPrivateByConstructor {

	public static void main(String[] args) {
		new Boy();

	}

}
