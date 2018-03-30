package chapter21_concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Meal {
	private final int id;

	public Meal(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "meal " + id;
	}
}

class Waiter implements Runnable {
	private Restaurant restaurant;

	public Waiter(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				synchronized (this) {
					while (restaurant.meal == null) {
						wait();
					}
				}
				System.out.println("waiter got " + restaurant.meal);
				restaurant.meal = null;
				synchronized (restaurant.chef) {
					restaurant.chef.notifyAll();
				}

			}
		} catch (InterruptedException e) {
			System.out.println("Waiter Interrupted");
		}
	}

}

class Chef implements Runnable {
	private Restaurant restaurant;

	public Chef(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	private int countDown = 10;

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				synchronized (this) {
					while (restaurant.meal != null) {
						wait();
					}
				}
				if (--countDown < 0) {
					System.out.println("over");
					restaurant.exec.shutdownNow();

				}
				System.out.println("Order up! ");
				restaurant.meal = new Meal(countDown);
				synchronized (restaurant.waiter) {
					restaurant.waiter.notifyAll();
				}
				TimeUnit.MILLISECONDS.sleep(100);

			}
		} catch (InterruptedException e) {
			System.out.println("Chef Interrupted");
		}

	}

}

public class Restaurant {
	Meal meal;
	Waiter waiter = new Waiter(this);
	Chef chef = new Chef(this);
	ExecutorService exec = Executors.newCachedThreadPool();

	public Restaurant() {
		exec.execute(waiter);
		exec.execute(chef);
	}

	public static void main(String[] args) {
		new Restaurant();
	}

}
