package chapter21_concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EvenChecker implements Runnable {
	private IntGenerator generator;
	private final int id;
	private int max_value = 20;

	public EvenChecker(IntGenerator g, int id) {
		generator = g;
		this.id = id;

	}

	@Override
	public void run() {
		while ((!generator.isCanceled()) && (--max_value > 0)) {
			int val = generator.next();
			if (val % 2 != 0) {
				System.out.println(Thread.currentThread().getName() + " found " + val + " is not even!");
				generator.cancel();
			}
		}
		System.out.println(Thread.currentThread().getName() + " exit when max_value is " + max_value
				+ " and generator.isCanceled() is " + generator.isCanceled());
	}

	public static void test(IntGenerator gp, int count) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < count; i++) {
			exec.execute(new EvenChecker(gp, i));
		}

		exec.shutdown();

	}

}
