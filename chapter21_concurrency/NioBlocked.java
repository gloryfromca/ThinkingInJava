package chapter21_concurrency;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousCloseException;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.SocketChannel;

public class NioBlocked implements Runnable {
	private final SocketChannel sc;

	public NioBlocked(SocketChannel sc) {
		this.sc = sc;
	}

	@Override
	public void run() {
		try {
			System.out.println("waiting for read() in " + this);
			sc.read(ByteBuffer.allocate(10));
		} catch (ClosedByInterruptException e) {
			System.out.println("ClosedByInterruptionException");
		} catch (AsynchronousCloseException e) {
			System.out.println("AsynchronousCloseException");
		} catch (IOException e) {
			System.out.println("IOException");
		}
		System.out.println("exiting from NIOBlocked.run() " + this);
	}

}
