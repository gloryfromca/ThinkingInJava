package chapter18;

import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

class Blip1 implements Externalizable {
	public Blip1() {
		System.out.println("init Blip1");
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		System.out.println("Blip1.writeExternal");
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		System.out.println("Blip1.readExternal");
	}

}

class Blip2 implements Externalizable {
	Blip2() {
		System.out.println("init Blip2");
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		System.out.println("Blip2.writeExternal");
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		System.out.println("Blip2.readExternal");
	}

}

class Blip3 implements Externalizable {
	private String a;
	private int i;

	public Blip3() {
		System.out.println("default init Blip3");
	}

	public Blip3(String a, int i) {
		System.out.println("args init Blip3");
		this.a = a;
		this.i = i;
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		System.out.println("Blip3.writeExternal");
		out.writeObject(a);
		out.writeObject(i);

	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		System.out.println("Blip3.readExternal");
		a = (String) in.readObject();
		i = (Integer) in.readObject();
	}

	@Override
	public String toString() {
		return a + ", " + i;
	}

}

public class Blips {
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		Blip1 blip1 = new Blip1();
		Blip2 blip2 = new Blip2();
		Blip3 blip3 = new Blip3("a", 2);

		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FilesForTest.objectMac));
		oos.writeObject(blip1);
		// oos.writeObject(blip2);
		oos.writeObject(blip3);
		oos.close();

		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FilesForTest.objectMac));
		blip1 = (Blip1) ois.readObject();
		// blip2 = (Blip2) ois.readObject();
		blip3 = (Blip3) ois.readObject();
		System.out.println(blip3);
	}
}
