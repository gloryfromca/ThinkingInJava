package chapter12;

public class FinallyAfterReturn {
	public int f() {
		try {
			return 5;
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			System.out.println("finally!");
		}
		return 0;
	}
	public static void main(String[] args) {
		System.out.println(new FinallyAfterReturn().f());
	}
	
}
