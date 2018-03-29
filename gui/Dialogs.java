package gui;

import static gui.SwingConsole.run;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

class Mydialogs extends JDialog {
	public Mydialogs(JFrame parent) {
		super(parent, "My Dialogs", true);
		setLayout(new FlowLayout());
		add(new JLabel("LOOK!"));
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		add(ok);
		setSize(150, 135);
	}

}

public class Dialogs extends JFrame {
	private JButton j1 = new JButton("show Dialog Box");
	private Mydialogs mydialogs = new Mydialogs(null);

	public Dialogs() {
		j1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mydialogs.setVisible(true);
			}
		});
		add(j1);
	}

	public static void main(String[] args) {
		run(new Dialogs(), 125, 75);
	}

}
