package chapter08_polymorphism;

class Useful{
	void f() {};
}
class MoreUseful extends Useful{
	void f() {};
	void g() {
		System.out.println("g");
	};
}

public class DowncastingInRunningTime {

	public static void main(String[] args) {
		Useful[] usefuls = {new Useful(), new MoreUseful()};
		
		usefuls[0].f();
		usefuls[1].f();
		
		// class Useful don't have g() method. 
		//usefuls[0].g();
		//usefuls[1].g();
		
		((MoreUseful) usefuls[1]).g();
		// will raise a ClassCastException error in run-time.
		((MoreUseful) usefuls[0]).g();
	}

}
