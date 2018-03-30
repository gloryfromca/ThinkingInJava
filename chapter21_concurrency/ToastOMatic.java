package chapter21_concurrency;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

class Toast {
	public enum Status {
		DRY, BUTTERED, JAMMED
	};

	private final int id;
	private Status status = Status.DRY;

	public Toast(int id) {
		this.id = id;
	}

	public void butter() {
		status = Status.BUTTERED;
	}

	public void jam() {
		status = Status.JAMMED;
	}

	public Status getStatus() {
		return status;
	}

	public int getId() {
		return id;
	}

	public String toString() {
		return "Toast " + id + ": " + status;
	}

}

class ToastQueue extends LinkedBlockingQueue<Toast> {
}

class Toaster implements Runnable {

	private ToastQueue toastQueue;
	private int count = 0;
	private Random rand = new Random(47);

	public Toaster(ToastQueue toastQueue) {
		this.toastQueue = toastQueue;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				TimeUnit.MILLISECONDS.sleep(100 + rand.nextInt(500));
				Toast t = new Toast(count++);
				toastQueue.put(t);
			}
		} catch (InterruptedException e) {
			System.out.println("Toaster interrupted!");
		}
		System.out.println("Toaster off");
	}

}

class Butterer implements Runnable {

	private ToastQueue dryQueue, butteredQueue;

	public Butterer(ToastQueue dryQueue, ToastQueue butteredQueue) {
		this.dryQueue = dryQueue;
		this.butteredQueue = butteredQueue;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Toast toast = dryQueue.take();
				toast.butter();
				butteredQueue.put(toast);

			}
		} catch (InterruptedException e) {
			System.out.println("butterer interrupted!");
		}
		System.out.println("butterer off");
	}

}

class Jammer implements Runnable {

	private ToastQueue butteredQueue, finishedQueue;

	public Jammer(ToastQueue butteredQueue, ToastQueue finishedQueue) {
		this.butteredQueue = butteredQueue;
		this.finishedQueue = finishedQueue;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Toast toast = butteredQueue.take();
				toast.jam();
				finishedQueue.put(toast);
			}
		} catch (InterruptedException e) {
			System.out.println("jammer interrupted!");
		}
		System.out.println("jammer off");
	}

}

class Eater implements Runnable {
	private ToastQueue finishedQueue;
	private int counter = 0;

	public Eater(ToastQueue finishedQueue) {
		this.finishedQueue = finishedQueue;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Toast toast = finishedQueue.take();
				if (toast.getId() != counter++ || toast.getStatus() != Toast.Status.JAMMED) {
					System.out.println(">>>> error: " + toast);
					System.exit(1);
				} else {
					System.out.println("Chomp!" + toast);
				}

			}
		} catch (InterruptedException e) {
			System.out.println("Eater Interrupted");
		}
		System.out.println("Eater off!");
	}

}

public class ToastOMatic {

	public static void main(String[] args) throws InterruptedException {
		ToastQueue dryQueue = new ToastQueue(), butteredQueue = new ToastQueue(), finishedQueue = new ToastQueue();
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new Toaster(dryQueue));
		exec.execute(new Butterer(dryQueue, butteredQueue));
		exec.execute(new Jammer(butteredQueue, finishedQueue));
		exec.execute(new Eater(finishedQueue));
		TimeUnit.SECONDS.sleep(5);
		exec.shutdownNow();

	}

}
