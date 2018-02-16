package chapter21;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CloseResource {

	public static void main(String[] args) throws IOException, InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		ServerSocket server = new ServerSocket(8989);
		InputStream is = new Socket("localhost", 8989).getInputStream();
		exec.execute(new IOBlocked(System.in));
		exec.execute(new IOBlocked(is));
		exec.shutdownNow();
		TimeUnit.SECONDS.sleep(1);
		is.close();
		TimeUnit.SECONDS.sleep(1);
		System.in.close();
		
	}

}
