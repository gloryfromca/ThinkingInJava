package chapter21;

import java.util.HashSet;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import javax.swing.text.StyledEditorKit.ForegroundAction;

class Car2 {
	private final int id;
	private boolean engine = false, driveTrain = false, wheels = false;

	public Car2(int id) {
		this.id = id;
	}

	public synchronized int getId() {
		return id;
	}

	public synchronized void addEngine() {
		engine = true;
	}

	public synchronized void addDriveTrain() {
		driveTrain = true;
	}

	public synchronized void addWheels() {
		wheels = true;
	}

	@Override
	public synchronized String toString() {
		return "Car " + id + " [ engine: " + engine + " driveTrain: " + driveTrain + " wheels: " + wheels + " ]";
	}

}

class CarQueue extends LinkedBlockingQueue<Car2> {

}

class ChassisBuilder implements Runnable {
	private CarQueue carQueue;
	private int counter = 0;

	public ChassisBuilder(CarQueue cq) {
		this.carQueue = cq;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				TimeUnit.MILLISECONDS.sleep(500);
				Car2 car = new Car2(counter++);
				System.out.println("ChassisBuilder created " + car);
				carQueue.put(car);
			}
		} catch (InterruptedException e) {
			System.out.println("Interrupted: ChassisBuilder");
		}
		System.out.println("ChassisBuilder off");
	}

}

class Assembler implements Runnable {
	private CarQueue chassisQueue, finishedQueue;
	private Car2 car;
	private CyclicBarrier barrier = new CyclicBarrier(4);
	private RobotPool pool;

	public Assembler(CarQueue cq, CarQueue fq, RobotPool p) {
		chassisQueue = cq;
		finishedQueue = fq;
		pool = p;
	}

	public Car2 car() {
		return car;
	}

	public CyclicBarrier barrier() {
		return barrier;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				car = chassisQueue.take();
				pool.hire(EngineRobot.class, this);
				pool.hire(DriveTrainRobot.class, this);
				pool.hire(WheelRobot.class, this);
				barrier.await();
				finishedQueue.put(car);
			}
		} catch (InterruptedException e) {
			System.out.println("Interrupted: Assembler");
		} catch (BrokenBarrierException e) {
			System.out.println("BrokenBarrierException");
		}
		System.out.println("Assembler off");
	}

}

abstract class Robot implements Runnable {
	private RobotPool pool;
	protected Assembler assembler;
	private volatile boolean engage = false;

	public Robot(RobotPool p) {
		this.pool = p;
	}

	public synchronized Robot assignAssembler(Assembler ab) {
		this.assembler = ab;
		return this;
	}

	public synchronized void engage() {
		this.engage = true;
		notifyAll();
	}

	abstract protected void perform();

	public synchronized void powerDown() throws InterruptedException {
		engage = false;
		assembler = null;
		pool.release(this);
		while (engage == false)
			wait();
	}

	@Override
	public void run() {
		try {
			powerDown();
			while (!Thread.interrupted()) {
				perform();
				assembler.barrier().await();
				powerDown();
			}
		} catch (InterruptedException e) {
			System.out.println("Interrupted: Robot");
		} catch (BrokenBarrierException e) {
			System.out.println("BrokenBarrierException");
		}

	}

}

class EngineRobot extends Robot {

	public EngineRobot(RobotPool p) {
		super(p);
	}

	@Override
	protected void perform() {
		System.out.println(this + " installing engine");
		assembler.car().addEngine();
	}

}

class DriveTrainRobot extends Robot {

	public DriveTrainRobot(RobotPool p) {
		super(p);
	}

	@Override
	protected void perform() {
		System.out.println(this + " installing driveTrain");
		assembler.car().addDriveTrain();
	}

}

class WheelRobot extends Robot {

	public WheelRobot(RobotPool p) {
		super(p);
	}

	@Override
	protected void perform() {
		System.out.println(this + " installing wheel");
		System.out.println("perform " + assembler.car());
		assembler.car().addWheels();
	}

}

class RobotPool {
	private HashSet<Robot> pool = new HashSet<Robot>();

	public synchronized void add(Robot r) {
		pool.add(r);
		notifyAll();
	}

	public synchronized void hire(Class<? extends Robot> robotType, Assembler ab) throws InterruptedException {
		for (Robot r : pool) {
			if (r.getClass().equals(robotType)) {
				pool.remove(r);
				r.assignAssembler(ab);
				r.engage();
				return;
			}
		}
			wait();
			hire(robotType, ab);
	}

	public synchronized void release(Robot r) {
		add(r);
	}
}

class Reporter implements Runnable {
	private CarQueue carQueue;

	public Reporter(CarQueue carQueue) {
		this.carQueue = carQueue;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				System.out.println(carQueue.take());
			}
		} catch (InterruptedException e) {
			System.out.println("Interrupted: Reporter");
		}
		System.out.println("reporter off");

	}

}

public class CarBuilder {

	public static void main(String[] args) throws InterruptedException {
		CarQueue cq = new CarQueue(), fq = new CarQueue();
		ExecutorService exec = Executors.newCachedThreadPool();
		RobotPool pool = new RobotPool();
		exec.execute(new EngineRobot(pool));
		exec.execute(new DriveTrainRobot(pool));
		exec.execute(new WheelRobot(pool));
		exec.execute(new Assembler(cq, fq, pool));
		exec.execute(new Reporter(fq));
		exec.execute(new ChassisBuilder(cq));
		TimeUnit.SECONDS.sleep(20);
		exec.shutdownNow();

	}

}
