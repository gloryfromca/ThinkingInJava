package chapter7;

class ContainPrivate {
	public void g() {
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
		pap.g();
	}

}
