package chapter05_initialization;

public class VarargsCauseCompileError {
	static void f(float i, Character... args) {
		System.out.println("fixed float arg");
	};

	static void f(Character... args) {
		System.out.println("varargs");
	};

	public static void main(String[] args) {
		// The method f(float, Character...) in the type VarargsLeadCompileError is not
		// applicable for the arguments (int, int)
		// f(1,2);

		f(1, '1');

		// The method f(float, Character[]) is ambiguous for the type
		// VarargsLeadCompileError
		// f('1', '1');
		
		f(1L, '1');

	}

}
