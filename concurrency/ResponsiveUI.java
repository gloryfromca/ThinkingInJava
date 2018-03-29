package concurrency;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ResponsiveUI extends Thread {
	private int n;

	public ResponsiveUI(int n) {
		this.n = n;
		start();
	}

	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(10);
			System.out.println("The result is: " + 2 * n);
		} catch (InterruptedException e) {
			System.out.println("interrupted!");
		}
	}

	public static void main(String[] args) throws IOException {
		new ResponsiveUI(10);
		System.in.read();
	}

}
