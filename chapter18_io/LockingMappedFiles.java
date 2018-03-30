package chapter18_io;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class LockingMappedFiles {
	final static int LENGTH = 0x8FFFFFF;
	static FileChannel fc;

	public static void main(String[] args) throws IOException {
		fc = new RandomAccessFile(new File(FilesForTest.inMac), "rw").getChannel();
		MappedByteBuffer mbbf = fc.map(FileChannel.MapMode.READ_WRITE, 0, LENGTH);
		for (int i = 0; i < LENGTH; i++) {
			mbbf.put((byte) 'x');
		}
		new LockedAndModified(mbbf, 0, LENGTH / 3);
		mbbf.position(0);
		mbbf.limit(LENGTH / 3);
		while (mbbf.hasRemaining()) {
			System.out.println((char)mbbf.get());
		}

	}

	private static class LockedAndModified extends Thread {
		private int start, end;
		private ByteBuffer bbf;

		public LockedAndModified(ByteBuffer mbbf, int start, int end) {
			this.start = start;
			this.end = end;
			mbbf.position(start);
			mbbf.limit(end);
			this.bbf = mbbf.slice();
			start();
			

		}

		@Override
		public void run() {
			try {
				FileLock fl = fc.lock();
				while (bbf.position() < bbf.limit() - 1) {
					bbf.put((byte) (bbf.get() + 1));
				}
				fl.release();

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

	}

}
