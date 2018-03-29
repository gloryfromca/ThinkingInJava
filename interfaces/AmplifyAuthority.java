package interfaces;
class BaseAuthority{
	int g(int i) {
		return i;
	}
	public int h(int i) {
		return i;
	}
}

public class AmplifyAuthority extends BaseAuthority {
	 public int g(int i) {
		return i;
	}
	 // you can just amplify authority.
	 //	 int h(int i) {
	 //	 		return i;
	 //	 }

}
