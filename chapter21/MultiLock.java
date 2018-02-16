package chapter21;

public class MultiLock {
	public synchronized void f(int count) {
		if (--count > 0) {
			System.out.println("count is " + count + ", will call g()");
			g(count);
		}
	}

	public synchronized void g(int count) {
		if (--count > 0) {
			System.out.println("count is " + count + ", will call f()");
			f(count);
		}
	}

	public static void main(String[] args) {
		final MultiLock ml = new MultiLock();
		new Thread() {
			public void run(){
				ml.f(10);
			}
		}.start();
		
	}

}
