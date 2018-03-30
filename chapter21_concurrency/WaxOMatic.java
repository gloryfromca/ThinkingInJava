package chapter21_concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Car {
	private boolean waxOn = false;

	public synchronized void waxed() {
		waxOn = true;
		notifyAll();
	};

	public synchronized void buffed() {
		waxOn = false;
		notifyAll();
	};

	public synchronized void waitingForWaxing() throws InterruptedException {
		while (waxOn == false)
			wait();
	}

	public synchronized void waitingForBuffing() throws InterruptedException {
		while (waxOn == true)
			wait();
	}
}

class WaxOff implements Runnable {
	private Car car;

	public WaxOff(Car c) {
		car = c;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				car.waitingForWaxing();
				System.out.println("Buffing...");
				TimeUnit.MILLISECONDS.sleep(200);
				System.out.println("buffing done!");
				car.buffed();
			}
			System.out.println("exit via while interrupt test");
		} catch (InterruptedException e) {
			System.out.println("InterruptedException");
		}
	}
}

class WaxOn implements Runnable {
	private Car car;

	public WaxOn(Car c) {
		car = c;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				car.waitingForBuffing();
				System.out.println("Waxing...");
				TimeUnit.MILLISECONDS.sleep(200);
				System.out.println("Waxing done!");
				car.waxed();
			}
			System.out.println("exit via while interrupt test");
		} catch (InterruptedException e) {
			System.out.println("InterruptedException");
		}
	}

}

public class WaxOMatic {

	public static void main(String[] args) throws InterruptedException {
		Car car = new Car();
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new WaxOn(car));
		exec.execute(new WaxOff(car));
		TimeUnit.SECONDS.sleep(2);
		exec.shutdownNow();
	}

}
