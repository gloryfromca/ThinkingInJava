package chapter22_gui;

import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import static chapter22_gui.SwingConsole.run;

public class LookAndFeel extends JFrame {
	private String[] choices = "Eeny Meeny Mickey Moe Larry Curly".split(" ");
	private Component[] samples = { new JButton("JButton"), new JTextField("JTextField"), new JLabel("JLabel"),
			new JCheckBox("KCheckBox"), new JComboBox(choices), };

	public LookAndFeel() {
		super("Look And Feel");
		setLayout(new FlowLayout());
		for (Component component : samples) {
			add(component);
		}
	}

	private static void usageError() {
		System.out.println("Usage:LookAndFeel [cross|system|motif]");
		System.exit(1);
	}

	public static void main(String[] args) {
		if (args.length == 0) {
			usageError();
		}
		if (args[0].equals("cross")) {
			try {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (args[0].equals("system")) {
			try {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (args[0].equals("motif")) {
			try {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			usageError();
		}
		run(new LookAndFeel(), 300, 300);
	}

}
