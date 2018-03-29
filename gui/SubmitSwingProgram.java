package gui;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class SubmitSwingProgram extends JFrame {
	static SubmitSwingProgram ssp;
	JLabel label;

	public SubmitSwingProgram() throws InterruptedException {
		super("helllo Swing");
		System.out.println("already set up window!");
		label = new JLabel("A label");
		add(label);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 200);
		System.out.println("in constructor, then sleep!");
		// event thread sleeps 3 seconds.
		TimeUnit.SECONDS.sleep(3);
		System.out.println("display!");
		setVisible(true);
		System.out.println(new Date());
	}

	public static void main(String[] args) throws InterruptedException {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				try {
					ssp = new SubmitSwingProgram();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		// main thread sleeps 6 seconds.
		TimeUnit.SECONDS.sleep(6);
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				System.out.println(new Date());
				ssp.label.setText("after 6-3=3 seconds");
			}
		});

	}

}
