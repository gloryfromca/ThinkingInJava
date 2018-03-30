package chapter21_concurrency;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Customer {
	private final int serviceTime;

	public Customer(int serviceTime) {
		this.serviceTime = serviceTime;

	}

	public int getServiceTime() {
		return serviceTime;
	}

	public String toString() {
		return "[" + serviceTime + "]";
	}

}

class CustomerLine extends ArrayBlockingQueue<Customer> {
	public CustomerLine(int maxLineSize) {
		super(maxLineSize);
	}

	@Override
	public String toString() {
		if (this.size() == 0)
			return "[Empty]";
		StringBuilder result = new StringBuilder();
		for (Customer customer : this) {
			result.append(customer);
		}
		return result.toString();
	}

}

class CustomerGenerator implements Runnable {
	private static Random rand = new Random(47);
	private CustomerLine customers;

	public CustomerGenerator(CustomerLine cl) {
		customers = cl;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(300));
				customers.put(new Customer(rand.nextInt(1000)));
			}
		} catch (InterruptedException e) {
			System.out.println("CustomerGenerator terminating");
		}

	}

}

class Teller implements Runnable, Comparable<Teller> {
	private static int counter;
	private final int id = counter++;
	private int customerServed = 0;
	private CustomerLine customers;
	private boolean servingCustomerLine = true;

	public Teller(CustomerLine cl) {
		customers = cl;
	}

	@Override
	public int compareTo(Teller o) {
		return customerServed < o.customerServed ? -1 : (customerServed > o.customerServed ? 1 : 0);

	}

	public synchronized void doSomethingelse() {
		customerServed = 0;
		servingCustomerLine = false;
	}

	public synchronized void serveCustomerLine() {
		System.out.println(this + " already serving!");
		servingCustomerLine = true;
		notifyAll();
	}

	@Override
	public String toString() {
		return "T" + id;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Customer customer = customers.take();
				TimeUnit.MILLISECONDS.sleep(customer.getServiceTime());
				synchronized (this) {
					customerServed++;
					while (!servingCustomerLine)
						wait();
				}
			}

		} catch (InterruptedException e) {
			System.out.println("Interruption");
		}
		System.out.println(this + " terminating");
	}

}

class TellerManager implements Runnable {
	private ExecutorService exec;
	private CustomerLine customers;
	private PriorityQueue<Teller> workingTellers = new PriorityQueue<Teller>();
	private Queue<Teller> tellersDoingOtherThings = new LinkedList<Teller>();
	private int adjustmentPeriod;

	public TellerManager(ExecutorService exec, CustomerLine cl, int adjustmentPeriod) {
		this.exec = exec;
		this.customers = cl;
		this.adjustmentPeriod = adjustmentPeriod;
		Teller teller = new Teller(customers);
		exec.execute(teller);
		workingTellers.add(teller);
	}

	private void reassignOneTeller() {
		Teller teller = workingTellers.poll();
		teller.doSomethingelse();
		tellersDoingOtherThings.offer(teller);

	}

	public void adjustTellerNumber() {
		if (customers.size() / workingTellers.size() > 2) {
			if (tellersDoingOtherThings.size() > 0) {
				Teller teller = tellersDoingOtherThings.remove();
				teller.serveCustomerLine();
				workingTellers.offer(teller);
				return;
			}
			Teller teller = new Teller(customers);
			exec.execute(teller);
			workingTellers.add(teller);
			return;
		}
		if (workingTellers.size() > 1 && customers.size() / workingTellers.size() < 2) {
			reassignOneTeller();
		}
		if ((customers.size() == 0)) {
			while (workingTellers.size() > 1)
				reassignOneTeller();
		}
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				TimeUnit.MILLISECONDS.sleep(adjustmentPeriod);
				adjustTellerNumber();
				System.out.print(customers.size() + ": " + customers + " { ");
				for (Teller teller : workingTellers) {
					System.out.print(teller + " ");
				}
				System.out.println("}");

			}
		} catch (InterruptedException e) {

		}
		System.out.println(this + " terminating");
	}

	@Override
	public String toString() {
		return "TellerManager";
	}

}

public class BankTellerSimulation {
	static final int MAX_LINE_SIZE = 50;
	static final int ADJUSTMENT_PERIOD = 1000;

	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		CustomerLine customers = new CustomerLine(MAX_LINE_SIZE);
		exec.execute(new CustomerGenerator(customers));
		exec.execute(new TellerManager(exec, customers, ADJUSTMENT_PERIOD));
		TimeUnit.SECONDS.sleep(20);
		exec.shutdownNow();

	}

}
