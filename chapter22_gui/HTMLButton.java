package chapter22_gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import static chapter22_gui.SwingConsole.run;

public class HTMLButton extends JFrame {
	private JButton b = new JButton("<html><b><font size=+2>" + "<center>Hello!<br><i>Press me now!");

	public HTMLButton() {
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				add(new JLabel("<html><i><font size=+4>Kapow!"));
				validate();
			}
		});
		setLayout(new FlowLayout());
		add(b);
	}

	public static void main(String[] args) {
		run(new HTMLButton(), 200, 500);
	}

}
