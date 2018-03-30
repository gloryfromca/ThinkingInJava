package chapter21_concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NaiveExceptionHandling {

	public static void main(String[] args) {
		try {
			//Exception in thread "pool-1-thread-1" 
			ExecutorService exec = Executors.newCachedThreadPool();
			exec.execute(new ExceptionThread());
		} catch (Exception e) {
			System.out.println("Exception has been handled!");
		}
	}

}
