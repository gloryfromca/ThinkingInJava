package chapter21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

class LiftOffRunner implements Runnable {
	private BlockingQueue<LiftOff> rockets;

	public LiftOffRunner(BlockingQueue<LiftOff> queue) {
		rockets = queue;
	}

	public void add(LiftOff lo) {
		try {
			rockets.put(lo);
		} catch (InterruptedException e) {
			System.out.println("Interruption from adding!");
		}
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				LiftOff rocket = rockets.take();
				rocket.run();
			}
		} catch (InterruptedException e) {
			System.out.println("Interruption from running!");
		}
	}

}

public class TestBlockingQueues {

	static void getkey() {
		try {
			new BufferedReader(new InputStreamReader(System.in)).readLine();
		} catch (IOException e) {
			System.out.println("IOException from getkey()");
			throw new RuntimeException(e);
		}
	}

	static void getkey(String msg) {
		System.out.println(msg);
		getkey();
	}

	static void test(String msg, BlockingQueue<LiftOff> queue) {
		LiftOffRunner runner = new LiftOffRunner(queue);
		Thread t = new Thread(runner);
		t.start();
		for (int i = 0; i < 5; i++) {
			runner.add(new LiftOff(3));
		}
		getkey("Press 'Enter' (" + msg + ")");
		t.interrupt();
		System.out.println("Finished " + msg + " test");

	}

	public static void main(String[] args) {
		test("LinkedBlockingQueue", new LinkedBlockingQueue<LiftOff>());
		test("ArrayBlockingQueue", new ArrayBlockingQueue<LiftOff>(3));
		test("SynchronousQueue", new SynchronousQueue<LiftOff>());

	}

}
