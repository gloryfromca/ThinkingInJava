package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ShowSerializeStaticArgs {
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		SerializeStaticArgs ssa = new SerializeStaticArgs();
		System.out.println(ssa);
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FilesForTest.objectMac));
		// you can comment out line below.
		// oos.writeObject(SerializeStaticArgs.class);
		// call by hand.
		SerializeStaticArgs.serializeStaticState(oos);
		oos.writeObject(ssa);
		oos.close();
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FilesForTest.objectMac));
		// you can comment out line below.
		// Class<SerializeStaticArgs> SSA = (Class<SerializeStaticArgs>)
		// ois.readObject();
		SerializeStaticArgs.derializeStaticState(ois);
		ssa = (SerializeStaticArgs) ois.readObject();
		System.out.println(ssa);
	}
}
