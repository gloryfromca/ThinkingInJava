package interfaces;
interface S3 {int f(int i);}
class C3  {
	// int f(int i) {return i;} 
	//will raise error.
	// you must add `public` prefix.
	public int f(int i) {
		return  i;
	}
}

public class InterfaceCollision extends C3 implements S3{

	
}
