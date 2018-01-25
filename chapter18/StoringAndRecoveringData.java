package chapter18;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class StoringAndRecoveringData {

	public static void main(String[] args) throws IOException {
		File file = new File("/home/zhanghui/BasicFileOutput");
		DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
		dos.writeInt(1);
		dos.writeLong(10L);
		dos.writeUTF("zhanghui");
		dos.close();
		DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
		System.out.println(dis.readInt());
		System.out.println(dis.readLong());
		System.out.println(dis.readUTF());
		dis.close();

	}
}
