package chapter22;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import static chapter22.SwingConsole.run;

public class Button1 extends JFrame {
	private JButton b1 = new JButton("Button1"), b2 = new JButton("Button2");;

	public Button1() {
		setLayout(new FlowLayout());
		add(b1);
		add(b2);
	}

	public static void main(String[] args) {
		run(new Button1(), 200, 100);
	}

}
