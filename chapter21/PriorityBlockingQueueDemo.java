package chapter21;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

class PrioritizedTask implements Runnable, Comparable<PrioritizedTask> {
	private Random rand = new Random(47);
	private static int counter = 0;
	private final int id = counter++;
	private int priority;

	public PrioritizedTask(int priority) {
		this.priority = priority;
	}

	@Override
	public int compareTo(PrioritizedTask o) {

		return priority < o.priority ? 1 : (priority > o.priority ? -1 : 0);
	}

	@Override
	public void run() {
		try {
			TimeUnit.MILLISECONDS.sleep(rand.nextInt(250));
		} catch (InterruptedException e) {

		}
		System.out.println(this);
	}

	@Override
	public String toString() {
		return String.format("[%1$-2d]", priority) + " Task" + id;
	}

	public static class EndSentinel extends PrioritizedTask {
		private ExecutorService exec;

		public EndSentinel(ExecutorService exec) {
			super(-1);
			this.exec = exec;
		}

		public void run() {
			System.out.println(this + " calling shutdownNow()");
			exec.shutdownNow();
		}
	}

}

class PrioritizedTaskProducer implements Runnable {
	private Random rand = new Random(47);
	private Queue<Runnable> queue;
	private ExecutorService exec;

	public PrioritizedTaskProducer(Queue<Runnable> q, ExecutorService exec) {
		queue = q;
		this.exec = exec;

	}

	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			queue.add(new PrioritizedTask(rand.nextInt(10)));
			Thread.yield();
		}
		try {

			for (int i = 0; i < 10; i++) {
				TimeUnit.MILLISECONDS.sleep(250);
				queue.add(new PrioritizedTask(10));
			}
			for (int i = 0; i < 10; i++) {
				queue.add(new PrioritizedTask(i));
			}
			queue.add(new PrioritizedTask.EndSentinel(exec));

		} catch (InterruptedException e) {

		}
		System.out.println("Finished PrioritizedTaskProducer");

	}

}

class PriortizedTaskConsumer implements Runnable {
	private PriorityBlockingQueue<Runnable> q;

	public PriortizedTaskConsumer(PriorityBlockingQueue<Runnable> q) {
		this.q = q;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				q.take().run();

			}

		} catch (InterruptedException e) {

		}
		System.out.println("Finished PriortizedTaskConsumer");
	}

}

public class PriorityBlockingQueueDemo {

	public static void main(String[] args) {
		Random rand = new Random(47);
		ExecutorService exec = Executors.newCachedThreadPool();
		PriorityBlockingQueue<Runnable> queue = new PriorityBlockingQueue<Runnable>();
		exec.execute(new PrioritizedTaskProducer(queue, exec));
		exec.execute(new PriortizedTaskConsumer(queue));

	}

}
