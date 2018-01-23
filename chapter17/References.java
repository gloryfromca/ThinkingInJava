package chapter17;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.LinkedList;

class VeryBig {
	String id;

	public VeryBig(String id) {
		this.id = id;
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("Finalizing " + id);
	}
}

public class References {
	private static ReferenceQueue<VeryBig> rq = new ReferenceQueue<VeryBig>();

	static void checkQueue() {
		Reference<? extends VeryBig> inq = rq.poll();
		if (inq != null) {
			System.out.println("In queue: " + inq.get());
		}
	}

	public static void main(String[] args) {
		final int SIZE = 10;
		LinkedList<WeakReference<VeryBig>> llw = new LinkedList<WeakReference<VeryBig>>();
		for (int i = 0; i < SIZE; i++) {
			llw.add(new WeakReference<VeryBig>(new VeryBig("llw id" + i), rq));
			System.out.println("just created: " + llw.getLast());
			checkQueue();
		}

		System.out.println(llw);
		System.out.println("before gc llw: " + llw.size() + " " + llw.get(1).get());

		LinkedList<SoftReference<VeryBig>> lls = new LinkedList<SoftReference<VeryBig>>();
		for (int i = 0; i < SIZE; i++) {
			lls.add(new SoftReference<VeryBig>(new VeryBig("lls id" + i), rq));
			System.out.println("just created: " + lls.getLast());
			checkQueue();
		}

		System.out.println(lls);
		System.out.println("before gc lls: " + lls.size());

		SoftReference<VeryBig> sr = new SoftReference<VeryBig>(new VeryBig("Soft"));
		WeakReference<VeryBig> wr = new WeakReference<VeryBig>(new VeryBig("Weak"));
		System.gc();
		System.out.println("after gc llw: " + llw.size() + " " + llw.get(1).get());
		System.out.println("after gc lls: " + lls.size());

		LinkedList<PhantomReference<VeryBig>> llp = new LinkedList<PhantomReference<VeryBig>>();
		for (int i = 0; i < SIZE; i++) {
			llp.add(new PhantomReference<VeryBig>(new VeryBig("lls id" + i), rq));
			System.out.println("just created: " + llp.getLast());
			checkQueue();
		}
		System.out.println(llp);
		System.out.println("gc llp: " + llp.size() + " " + llp.get(1).get());

	}
}
