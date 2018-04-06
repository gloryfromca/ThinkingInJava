package chapter22_gui;

import static chapter22_gui.SwingConsole.run;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;

public class LongRunningTask extends JFrame {
	private JButton b1 = new JButton("Start Long Running Task");
	private JButton b2 = new JButton("End Long Running Task");

	public LongRunningTask() {
		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					TimeUnit.SECONDS.sleep(5);
				} catch (InterruptedException s) {
					System.out.println("Task interrupted!");
					return;
				}
				System.out.println("Task completed!");
			}
		});
		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Thread.currentThread().interrupt();
			}
		});
		setLayout(new FlowLayout());
		add(b1);
		add(b2);

	}

	public static void main(String[] args) {

		run(new LongRunningTask(), 200, 150);
	}

}
