package chapter22;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import static chapter22.SwingConsole.run;

import java.awt.BorderLayout;

public class TabbedPane1 extends JFrame {
	private String[] names = { "zhang", "han", "zhao", "jiang", "li" };
	private JTabbedPane tabs = new JTabbedPane();
	private JTextField txt = new JTextField(20);

	public TabbedPane1() {
		int i = 0;
		for (String name : names) {
			tabs.addTab(name, new JButton("Tabbed pane " + i++));
		}
		tabs.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				txt.setText("Tab selected: " + tabs.getSelectedIndex());
			}
		});
		add(BorderLayout.SOUTH, txt);
		add(tabs);

	}

	public static void main(String[] args) {
		run(new TabbedPane1(), 400, 250);
	}

}
