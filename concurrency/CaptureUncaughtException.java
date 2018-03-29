package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

class ExceptionThread2 implements Runnable {

	@Override
	public void run() {
		Thread t = Thread.currentThread();
		System.out.println("run by " + t.getName());
		System.out.println("eh = " + t.getUncaughtExceptionHandler());
		throw new RuntimeException();

	}

}

class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		Thread current = Thread.currentThread();
		System.out.println("exception handler is " + current.getName());
		System.out.println("Caught!");
	}

}

class HandlerExceptionThreadFactory implements ThreadFactory {

	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r);
		t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
		return t;
	}

}

public class CaptureUncaughtException {

	public static void main(String[] args) {
		System.out.println("main() thread name is " + Thread.currentThread().getName());
		ExecutorService exec = Executors.newCachedThreadPool(new HandlerExceptionThreadFactory());
		exec.execute(new ExceptionThread2());
	}

}
