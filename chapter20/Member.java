package chapter20;

@DBTable(name = "MEMBER")
public class Member {
	@SQLString(30)
	String firstname;
	@SQLString(50)
	String lasttname;
	@SQLInteger
	Integer age;
	@SQLString(value = 30, constraints = @Constraints(primaryKey = true))
	String handle;
	static int memberCount;

	public String gethandle() {
		return handle;
	}

	public String getfirstname() {
		return firstname;
	}

	public String getlastname() {
		return lasttname;
	}

	public int getage() {
		return age;
	}

}
