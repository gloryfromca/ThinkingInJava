package reusing;

class ContainPrivate {
	private void g() {
		finalFunc();
	}

	// private means final
	private void finalFunc() {
		System.out.println("ContainPrivate:finalFunc");
		
	}
}

public class PrivateAndPublic extends ContainPrivate {

	public void finalFunc() {
		System.out.println("PrivateAndPublic:finalFunc");
	}

	public void g() {
		finalFunc();
	}
	// access-modifier isn't a part of MethodSignature
	// private void g() {}
	
	public static void main(String[] args) {
		PrivateAndPublic pap = new PrivateAndPublic();
		ContainPrivate cp = new PrivateAndPublic();
		// because private method is final method, so
		// JVM won't do late-binding. 
		// cp don't have access to a base-class' private-method. 
		// so code below will raise compilation error. 
		//cp.g();
		pap.g();
	}



}
