package chapter21;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Philosopher implements Runnable {
	private Chopstick left;
	private Chopstick right;
	private final int id;
	private final int ponderFactor;
	private Random random = new Random(47);

	private void pause() throws InterruptedException {
		if (ponderFactor == 0)
			return;
		TimeUnit.MILLISECONDS.sleep(random.nextInt(ponderFactor * 250));

	}

	public Philosopher(Chopstick left, Chopstick right, int id, int ponderFactor) {
		this.left = left;
		this.right = right;
		this.id = id;
		this.ponderFactor = ponderFactor;
	}

	@Override
	public String toString() {
		return "Philosopher " + id;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				System.out.println(this + " is thinking!");
				pause();
				System.out.println(this + " grabbing left");
				left.take();
				System.out.println(this + " grabbing right");
				right.take();
				System.out.println(this + " is eating");
				pause();
				right.drop();
				left.drop();

			}
		} catch (InterruptedException e) {
			System.out.println(this + " Interrupted!");
		}
	}

}
