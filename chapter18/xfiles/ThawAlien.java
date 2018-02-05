package chapter18.xfiles;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import chapter18.FilesForTest;

public class ThawAlien {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(FilesForTest.FreezeAlien));
		Object mystery = in.readObject();
		System.out.println(mystery.getClass());
	}

}
