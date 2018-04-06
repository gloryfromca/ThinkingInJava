package chapter22_gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.ProgressMonitor;
import javax.swing.SwingUtilities;

import net.mindview.util.TaskManager;

import static chapter22_gui.SwingConsole.run;

class MonitoredCallable implements Callable<String> {
	private static int counter;
	private final int id = counter++;
	private final ProgressMonitor monitor;
	private final static int MAX = 8;

	public MonitoredCallable(ProgressMonitor monitor) {
		this.monitor = monitor;
		monitor.setNote(toString());
		monitor.setMaximum(MAX - 1);
		monitor.setMillisToPopup(500);
	}

	@Override
	public String call() throws Exception {
		System.out.println(this + " started");
		try {
			for (int i = 0; i < MAX; i++) {
				TimeUnit.SECONDS.sleep(1);
				if (monitor.isCanceled()) {
					Thread.currentThread().interrupt();
				}
				final int progress = i;
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						monitor.setProgress(progress);
					}
				});
			}

		} catch (InterruptedException e) {
			monitor.close();
			System.out.println(this + " interrupted");
			return "Result: " + this + " interrupted";
		}
		System.out.println(this + " completed");
		return "Result: " + this + " completed";

	}

	public String toString() {
		return "Task " + id;
	}
}

public class MonitoredLongRunningCallable extends JFrame {
	private JButton b1 = new JButton("Start Long Running Task");
	private JButton b2 = new JButton("End Long Running Task");
	private JButton b3 = new JButton("get results");
	private TaskManager<String, MonitoredCallable> manager = new TaskManager<String, MonitoredCallable>();

	public MonitoredLongRunningCallable() {
		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MonitoredCallable task = new MonitoredCallable(
						new ProgressMonitor(MonitoredLongRunningCallable.this, "Long-Runining Task", "", 0, 100));
				manager.add(task);
				System.out.println(this + " added to the queue");
			}

		});
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MonitoredCallable task = new MonitoredCallable(
						new ProgressMonitor(MonitoredLongRunningCallable.this, "Long-Running Task", "", 0, 0));
				manager.add(task);
				System.out.println(task + " added to the queue");
			}
		});
		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (String result : manager.purge()) {
					System.out.println(result);
				}
			}
		});
		b3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (String result : manager.getResults()) {
					System.out.println(result);
				}
			}
		});

		setLayout(new FlowLayout());
		add(b1);
		add(b2);
		add(b3);

	}

	public static void main(String[] args) {
		run(new MonitoredLongRunningCallable(), 200, 150);
	}

}
