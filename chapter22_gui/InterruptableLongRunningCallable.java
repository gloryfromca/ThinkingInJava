package chapter22_gui;

import static chapter22_gui.SwingConsole.run;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Callable;

import javax.swing.JButton;
import javax.swing.JFrame;

import net.mindview.util.TaskManager;

class CallableTask extends Task implements Callable<String> {

	@Override
	public String call() throws Exception {
		run();
		return "Return value of " + this;
	}

}

public class InterruptableLongRunningCallable extends JFrame {
	private JButton b1 = new JButton("Start Long Running Task");
	private JButton b2 = new JButton("End Long Running Task");
	private JButton b3 = new JButton("get results");
	private TaskManager<String, CallableTask> manager = new TaskManager<String, CallableTask>();

	public InterruptableLongRunningCallable() {
		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CallableTask task = new CallableTask();
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
		run(new InterruptableLongRunningCallable(), 200, 150);
	}

}
