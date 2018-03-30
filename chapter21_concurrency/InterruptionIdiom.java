package chapter21_concurrency;

import java.util.concurrent.TimeUnit;

class NeedsCleanUp {
	private int id;

	public NeedsCleanUp(int id) {
		this.id = id;
	}

	public void cleanUp() {
		System.out.println(id + "clean up!");
	}

}

class Blocked3 implements Runnable {

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				try {
					NeedsCleanUp nc1 = new NeedsCleanUp(1);
					System.out.println("nc1 just sleep 3 seconds!");
					TimeUnit.SECONDS.sleep(3);
					try {
						NeedsCleanUp nc2 = new NeedsCleanUp(2);
						System.out.println("nc2 need to do complex calculation!");
						int s = 0;
						for (int i = 0; i < 10000000; i++) {
							s = s + 1;
						}
					} finally {
						System.out.println("call nc2.cleanUp() to clean resources opened by nc2!");
					}

				} finally {
					System.out.println("call nc1.cleanUp() to clean resources opened by nc1!");
				}
			}
			System.out.println("exiting via while condition!");

		} catch (InterruptedException e) {
			System.out.println("Interruption occur in Interruptable operations!");
		}
	}

}

public class InterruptionIdiom {

	public static void main(String[] args) throws InterruptedException {
		Thread thread = new Thread(new Blocked3());
		thread.start();
		TimeUnit.MILLISECONDS.sleep(3006);
		thread.interrupt();
		TimeUnit.SECONDS.sleep(3);
		System.out.println("========\nsleep until the first thread is over!");
		thread = new Thread(new Blocked3());
		thread.start();
		TimeUnit.SECONDS.sleep(2);
		thread.interrupt();

	}

}
