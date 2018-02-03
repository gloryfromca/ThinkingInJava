package chapter18;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerialCtl implements Serializable {

	private String s;
	private transient int i;

	public SerialCtl(String s, int i) {
		this.s = s;
		this.i = i;
	}

	public String toString() {
		return s + i;
	}

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
		out.writeObject(i);
	}

	private void readObject(ObjectInputStream in) throws ClassNotFoundException, IOException {
		in.defaultReadObject();
		i = (int) in.readObject();
	}

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		SerialCtl sc = new SerialCtl("zhanghui", 117);
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FilesForTest.objectMac));
		oos.writeObject(sc);
		;
		oos.close();
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FilesForTest.objectMac));
		sc = (SerialCtl) ois.readObject();
		System.out.println(sc);
	}

}
