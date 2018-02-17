package chapter21;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class TaskPortion implements Runnable {
	private static int counter = 0;
	private final int id = counter++;
	private static Random rand = new Random(47);
	private final CountDownLatch latch;

	public void doWork() throws InterruptedException {
		TimeUnit.MILLISECONDS.sleep(rand.nextInt(2000));
		System.out.println(this + " completed");
	}

	public TaskPortion(CountDownLatch latch) {
		this.latch = latch;
	}

	@Override
	public String toString() {
		return String.format("%1$-3d", id);
	}

	@Override
	public void run() {
		try {
			doWork();
			latch.countDown();
		} catch (InterruptedException e) {
			System.out.println("Interrupted!");

		}

	}

}

class WaitingTask implements Runnable {
	private static int counter = 0;
	private final int id = counter++;
	private CountDownLatch latch;

	public WaitingTask(CountDownLatch latch) {
		this.latch = latch;

	}

	@Override
	public String toString() {
		return String.format("waitingTask %1$-3d", id);
	}

	@Override
	public void run() {
		try {
			latch.await();
			System.out.println("Latch barrier passed for " + this);
		} catch (InterruptedException e) {
			System.out.println(this + " Interrupted!");
		}
	}

}

public class CountDownDemo {
	static final int SIZE = 100;

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		CountDownLatch latch = new CountDownLatch(SIZE);
		for (int i = 0; i < 10; i++) {
			exec.execute(new WaitingTask(latch));
		}
		for (int i = 0; i < SIZE; i++) {
			exec.execute(new TaskPortion(latch));
		}
		System.out.println("Launched all Tasks");
		exec.shutdown();

	}

}
