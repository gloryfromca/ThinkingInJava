package chapter18;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class SerializeStaticArgs implements Serializable {
	private static int i = 10;

	@Override
	public String toString() {
		return this.getClass() + " i: " + i;
	}

	public SerializeStaticArgs() {
		this.i = 118;
	}

	public static void serializeStaticState(ObjectOutputStream oos) throws IOException {
		// oos.writeInt(i);
		oos.writeInt(99);

	}

	public static void derializeStaticState(ObjectInputStream ois) throws IOException {
		i = ois.readInt();
	}

}
