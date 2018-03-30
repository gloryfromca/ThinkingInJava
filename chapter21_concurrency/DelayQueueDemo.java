package chapter21_concurrency;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class DelayedTask implements Runnable, Delayed {
	private static int counter = 0;
	private final int id = counter++;
	private final int delta;
	private final long trigger;

	public DelayedTask(int delta) {
		this.delta = delta;
		this.trigger = System.nanoTime() + TimeUnit.NANOSECONDS.convert(delta, TimeUnit.MILLISECONDS);

	}

	@Override
	public int compareTo(Delayed o) {
		DelayedTask dt = (DelayedTask) o;
		if (trigger < dt.trigger)
			return -1;
		if (trigger > dt.trigger)
			return 1;
		return 0;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		return unit.convert(trigger - System.nanoTime(), TimeUnit.NANOSECONDS);
	}

	@Override
	public void run() {
		System.out.println(this + " ");
	}

	@Override
	public String toString() {
		return String.format("[%1$-4d]", delta) + " Task " + id;
	}

	public static class EndSentinel extends DelayedTask {
		ExecutorService exec;

		public EndSentinel(int delta, ExecutorService exec) {
			super(delta);
			this.exec = exec;
		}

		@Override
		public void run() {
			System.out.println(this + " calling shutdownnow()");
			exec.shutdownNow();
		}

	}

}

class DelayedTaskConsumer implements Runnable {
	private DelayQueue<DelayedTask> dq;

	public DelayedTaskConsumer(DelayQueue<DelayedTask> dq) {
		this.dq = dq;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				dq.take().run();
			}
		} catch (InterruptedException e) {
		}
		System.out.println("Finished DelayedTaskConsumer");
	}

}

public class DelayQueueDemo {

	public static void main(String[] args) {
		Random random = new Random(47);
		ExecutorService exec = Executors.newCachedThreadPool();
		DelayQueue<DelayedTask> dq = new DelayQueue<DelayedTask>();
		for (int i = 0; i < 20; i++) {
			dq.put(new DelayedTask(random.nextInt(5000)));
		}
		dq.put(new DelayedTask.EndSentinel(5000, exec));
		exec.execute(new DelayedTaskConsumer(dq));

	}

}
