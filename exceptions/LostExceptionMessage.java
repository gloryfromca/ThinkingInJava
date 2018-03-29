package exceptions;

public class LostExceptionMessage {
	@SuppressWarnings("finally")
	public int f() throws Exception {
		try {
			return 5/0;
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			throw new Exception("previous exception will be dropped!");
			}
	}

	public static void main(String[] args) throws Exception {
		new LostExceptionMessage().f();
	}

}
