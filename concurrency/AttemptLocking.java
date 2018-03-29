package concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import com.sun.media.sound.AlawCodec;

public class AttemptLocking {
	private ReentrantLock lock = new ReentrantLock();

	public void untimed() {
		boolean captured = lock.tryLock();
		try {
			System.out.println("tryLock() is " + captured);
		} finally {
			if (captured)
				lock.unlock();

		}

	}

	public void timed() {
		boolean captured = false;
		try {
			captured = lock.tryLock(2, TimeUnit.SECONDS);
			System.out.println("tryLock(2, seconds) is " + captured);

		} catch (InterruptedException e) {
			System.out.println("interrupted!");
		} finally {
			if (captured)
				lock.unlock();
		}

	}

	public static void main(String[] args) {
		AttemptLocking atl = new AttemptLocking();
		atl.timed();
		atl.untimed();
		new Thread() {
			{
				setDaemon(true);
			}

			public void run() {
				atl.lock.lock();
			};

		}.start();
		Thread.yield();
		System.out.println("the second task:");
		atl.untimed();
		atl.timed();

	}

}
