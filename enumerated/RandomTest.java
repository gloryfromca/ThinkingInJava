package enumerated;

enum Activity {
	SINGING, LYING, FLYING
}

public class RandomTest {
	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			System.out.println(Enums.random(Activity.class));
		}
	}

}
