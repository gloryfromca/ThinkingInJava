package chapter7;

class SpaceShipControl {
	private int x;
	private int y;

	public void up() {
		System.out.println("up");
		x++;
	}

	public void down() {
		System.out.println("down");
		x--;
	}

	public void left() {
		System.out.println("left");
		y--;
	}

	public void right() {
		System.out.println("right");
		y++;
	}

	// you can access private variables by public method when you can't access
	// privates directly.
	// because you can access public method, the public method can access privates
	// of the current instance directly.
	public int x() {
		return x;
	}

	public int y() {
		return y;
	}
}

public class SpaceShipDelegation {
	SpaceShipControl sc = new SpaceShipControl();

	public void up() {
		sc.up();
	}

	public void down() {
		sc.down();
	}

	public void left() {
		sc.left();
	}

	public void right() {
		sc.right();
	}

	public void location() {
		System.out.println("x:" + sc.x() + " y:" + sc.y());
	}

	public static void main(String[] args) {
		SpaceShipDelegation sd = new SpaceShipDelegation();
		sd.up();
		sd.left();
		sd.right();
		sd.left();
		sd.location();
	}

}
