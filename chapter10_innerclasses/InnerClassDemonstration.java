package chapter10_innerclasses;

class InnerClass {
	public class PublicInnerClass {
		public PublicInnerClass() {
			System.out.println("PublicInnerClass");
		}
	};

	protected class ProtectedInnerClass {
		public ProtectedInnerClass() {
			System.out.println("ProtectedInnerClass");
		}
	};

	class DefaultInnerClass {
		public DefaultInnerClass() {
			System.out.println("DefaultInnerClass");
		}
	};

	private class PrivateInnerClass {
		public PrivateInnerClass() {
			System.out.println("PrivateInnerClass");
		}
	};

	public PrivateInnerClass getPrivateInnerClass() {
		return new PrivateInnerClass();
	}

	public DefaultInnerClass getDefaultInnerClass() {
		return new DefaultInnerClass();
	}

}

public class InnerClassDemonstration {

	public static void main(String[] args) {
		InnerClass ci = new InnerClass();

		ci.new PublicInnerClass();
		ci.new ProtectedInnerClass();
		ci.new DefaultInnerClass();
		// private class is just created by code in outer-class.
		// ci.new PrivateInnerClass();

		System.out.println();
		ci.getDefaultInnerClass();
		InnerClass.DefaultInnerClass id = ci.getDefaultInnerClass();
		ci.getPrivateInnerClass();
		// private inner class is not visible.
		// InnerClass.PrivateInnerClass ip = ci.getPrivateInnerClass();

	}

}
