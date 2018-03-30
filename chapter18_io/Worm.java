package chapter18_io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Random;

class Data implements Serializable {
	private int n;

	public Data(int n) {
		this.n = n;
	}

	@Override
	public String toString() {
		return Integer.toString(n);
	}
}

public class Worm implements Serializable {
	private static Random random = new Random();
	private Data[] d = { new Data(random.nextInt(10)), new Data(random.nextInt(10)), new Data(random.nextInt(10)), };
	private Worm next;
	private char c;

	public Worm(int i, char j) {
		System.out.println("Worm constructor: " + i);
		this.c = j;
		if (i-- > 0)
			next = new Worm(i, (char) (j + 1));

	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder(" ");
		result.append(c);
		result.append(": [");
		for (Data data : d) {
			result.append(data);
			result.append(",");
		}
		result.append("]");
		if (next != null) {
			result.append(next);
		}
		return result.toString();
	}

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		Worm w = new Worm(5, 'e');
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FilesForTest.objectMac));
		out.writeObject("zhanghui\n");
		out.writeObject(w);
		out.close();
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(FilesForTest.objectMac));
		String inString = (String) in.readObject();
		Worm wm = (Worm) in.readObject();
		System.out.println(inString);
		System.out.println(wm);
		in.close();

		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bout);
		oos.writeObject("xiaoming");
		oos.writeObject(wm);
		oos.flush();
		ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bout.toByteArray()));
		inString = (String) ois.readObject();
		wm = (Worm) ois.readObject();
		System.out.println(inString);
		System.out.println(wm);

	}

}
