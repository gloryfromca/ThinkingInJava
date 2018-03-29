package io;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

public class FormattedMemoryInput {

	public static void main(String[] args) {
		try {
			DataInputStream di = new DataInputStream(new ByteArrayInputStream("zhanghui".getBytes()));
			while (true) {
				System.out.println(di.readInt());
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

}
