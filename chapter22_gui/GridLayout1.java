package chapter22_gui;

import static chapter22_gui.SwingConsole.run;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GridLayout1 extends JFrame {
	public GridLayout1() {
		setLayout(new GridLayout(7, 3));
		for (int i = 0; i < 20; i++) {
			add(new JButton("button" + i));
		}
	}

	public static void main(String[] args) {
		run(new GridLayout1(), 300, 300);
	}

}
