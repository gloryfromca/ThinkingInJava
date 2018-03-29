package concurrency;

import java.util.concurrent.TimeUnit;

class Sleeper extends Thread {
	private String name;

	public Sleeper(String name) {
		this.name = name;
		start();
	}

	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			System.out.println("sleeping is interrupted!");
			return;
		}
		System.out.println("awakened!");

	}

}

class Joiner extends Thread {
	private String name;
	private Sleeper sleeper;

	public Joiner(String name, Sleeper sleeper) {
		this.name = name;
		start();
		this.sleeper = sleeper;
	}

	@Override
	public void run() {
		try {
			System.out.println("I will sleep!");
			sleeper.join();
		} catch (InterruptedException e) {
			System.out.println("Joiner has been Interrupted!");
		} finally {
			System.out.println("sleeping time has passed!");
		}

	}

}

public class Joining {

	public static void main(String[] args) {
		String name = "zhanghui";
		Sleeper sleepy = new Sleeper(name);
		Joiner joiner = new Joiner(name, sleepy);
		sleepy.interrupt();
	}

}
