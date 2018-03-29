package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

public class Logon implements Serializable {
	private String username;
	private Date time;
	private transient String passwd;

	public Logon(String username, String passwd) {
		this.passwd = passwd;
		this.username = username;
		this.time = new Date();
	}

	@Override
	public String toString() {
		return "passwd: " + passwd + ", username: " + username + ", time: " + time;
	}

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		Logon login = new Logon("zhanghui", "password");
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FilesForTest.objectMac));
		oos.writeObject(login);
		oos.close();
		System.out.println(login);

		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FilesForTest.objectMac));
		login = (Logon) ois.readObject();
		System.out.println(login);

	}

}
