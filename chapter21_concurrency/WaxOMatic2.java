package chapter21_concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Car1 {
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	public boolean waxOn = false;

	public void ActionIsOver() {
		lock.lock();
		try {
			condition.signalAll();
		} finally {
			lock.unlock();
		}

	}

	public void waitFor(String waitFor) throws InterruptedException {

		lock.lock();
		boolean temp = false;
		if (waitFor == "buffing") {
			temp = true;
		}
		try {
			while (waxOn == temp) {
				System.out.println("await...");
				condition.await();
			}
		} finally {
			lock.unlock();
		}

	}

}

class WaxOff1 implements Runnable {
	private Car1 car1;

	public WaxOff1(Car1 c) {
		car1 = c;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				car1.waitFor("waxing");
				System.out.println("Buffing...");
				TimeUnit.MILLISECONDS.sleep(200);
				System.out.println("buffing done!");
				car1.waxOn = false;
				car1.ActionIsOver();

			}
			System.out.println("exit via while interrupt test");
		} catch (InterruptedException e) {
			System.out.println("InterruptedException");
		}
	}
}

class WaxOn1 implements Runnable {
	private Car1 car1;

	public WaxOn1(Car1 c) {
		car1 = c;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				car1.waitFor("buffing");
				System.out.println("Waxing...");
				TimeUnit.MILLISECONDS.sleep(200);
				System.out.println("Waxing done!");
				car1.waxOn = true;
				car1.ActionIsOver();
			}
			System.out.println("exit via while interrupt test");
		} catch (InterruptedException e) {
			System.out.println("InterruptedException");
		}
	}

}

public class WaxOMatic2 {

	public static void main(String[] args) throws InterruptedException {
		Car1 car1 = new Car1();
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new WaxOn1(car1));
		exec.execute(new WaxOff1(car1));
		TimeUnit.SECONDS.sleep(2);
		exec.shutdownNow();
	}

}
