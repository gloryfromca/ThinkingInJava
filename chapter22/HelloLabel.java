package chapter22;

import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class HelloLabel {

	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("Hello Swing");
		System.out.println("after initializing JFrame()");
		TimeUnit.SECONDS.sleep(4);
		JLabel label = new JLabel("A label");
		frame.add(label);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 100);
		frame.setVisible(true);
		System.out.println("after setVisible()");
		TimeUnit.SECONDS.sleep(4);
		label.setText("This is different!");

	}

}
