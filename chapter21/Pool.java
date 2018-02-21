package chapter21;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Pool<T> {
	private int size;
	private List<T> items = new ArrayList<T>();
	private Semaphore semaphore;
	private volatile boolean[] checkout;

	public Pool(Class<T> classObject, int size) throws InstantiationException, IllegalAccessException {
		this.size = size;
		this.checkout = new boolean[size];
		semaphore = new Semaphore(size);
		items = new ArrayList<T>();
		for (int i = 0; i < size; i++) {
			items.add(classObject.newInstance());
		}
	}

	private synchronized boolean releaseItem(T item) {
		int index = items.indexOf(item);
		if (index == -1)
			return false;
		if (checkout[index]) {
			checkout[index] = false;
			return true;
		}
		return false;
	}

	public void checkIn(T item) {
		if (releaseItem(item))
			semaphore.release();
	}

	private synchronized T getItem() {
		for (int i = 0; i < size; i++) {
			if (!checkout[i]) {
				checkout[i] = true;
				return items.get(i);
			}
		}
		return null;
	}

	public T checkOut() throws InterruptedException {
		semaphore.acquire();
		return getItem();
	}

}
