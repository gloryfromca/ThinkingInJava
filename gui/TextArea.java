package gui;

import static gui.SwingConsole.run;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TextArea extends JFrame {
	private JButton b = new JButton("add data");
	private JButton c = new JButton("clear data");
	private JTextArea t = new JTextArea(20, 40);

	public TextArea() {
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < 10; i++)
					t.append("line: " + i + "\n");
			}
		});
		c.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				t.setText("");
			}
		});
		setLayout(new FlowLayout());
		add(new JScrollPane(t));
		add(b);
		add(c);

	}

	public static void main(String[] args) {
		run(new TextArea(), 300, 200);
	}

}
