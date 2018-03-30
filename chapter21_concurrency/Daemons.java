package chapter21_concurrency;

import java.util.concurrent.TimeUnit;

class DaemonSpawn implements Runnable {

	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

class Daemon implements Runnable {
	private Thread[] ts = new Thread[10];

	@Override
	public void run() {
		for (int i = 0; i < ts.length; i++) {
			ts[i] = new Thread(new Daemon());
			ts[i].start();
		}
		for (int i = 0; i < ts.length; i++) {
			System.out.println("ts[" + i + "].isDaemon() = " + ts[i].isDaemon());
		}

	}

}

public class Daemons {

	public static void main(String[] args) {
		Thread t = new Thread(new Daemon());
		t.setDaemon(true);
		t.start();

	}

}
