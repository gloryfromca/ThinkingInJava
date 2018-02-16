package chapter21;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;


public class NioInterruption {

	public static void main(String[] args) throws IOException, InterruptedException {
		ServerSocket server = new ServerSocket(8989);
		ExecutorService exec = Executors.newCachedThreadPool();
		InetSocketAddress isa = new InetSocketAddress("localhost", 8989);
		SocketChannel sc1 = SocketChannel.open(isa);
		SocketChannel sc2 = SocketChannel.open(isa);
		Future<?> f = exec.submit(new NioBlocked(sc1));
		exec.execute(new NioBlocked(sc2));
		exec.shutdown();
		TimeUnit.SECONDS.sleep(1);
		f.cancel(true);
		TimeUnit.SECONDS.sleep(1);
		sc2.close();
	}

}
