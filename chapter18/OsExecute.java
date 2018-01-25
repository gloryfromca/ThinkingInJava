package chapter18;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class OsExecuteException extends RuntimeException {
	public OsExecuteException(String why) {
		super(why);
	}
}

public class OsExecute {
	public static void command(String command) {
		boolean isErr = false;
		try {
			Process p = new ProcessBuilder(command.split(" ")).start();
			BufferedReader pi = new BufferedReader(new InputStreamReader(p.getInputStream()));
			BufferedReader pe = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			String s;
			while ((s = pi.readLine()) != null) {
				System.out.println(s);
			}
			String o;
			while ((o = pe.readLine()) != null) {
				System.out.println(o);
				isErr = true;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		if (isErr) {
			throw new OsExecuteException("Error executing " + command);
		}

	}

	public static void main(String[] args) {
		command("ls");
		command("pwd");
		// command("javap chapter18.OsExecute");
		// command("ll");

	}

}
