package chapter21_concurrency;

import java.sql.Time;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class BlockedMutex {
	private Lock lock = new ReentrantLock();

	public BlockedMutex() {
		lock.lock();
	}

	public void f() {
		try {
			lock.lockInterruptibly();
		} catch (InterruptedException e) {
			System.out.println("Interrupted from lock acquisition in f()");
		}

	}

}

class Blocked2 implements Runnable {
	BlockedMutex bm = new BlockedMutex();

	@Override
	public void run() {
		System.out.println("try to run blockedMutex.f()...");
		bm.f();
		System.out.println("broken out of blocked call");

	}

}

public class Interruption2 {

	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread(new Blocked2());
		t.start();
		TimeUnit.SECONDS.sleep(1);
		System.out.println("Issuing t.interrupt()");
		t.interrupt();
		
	}

}
