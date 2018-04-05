package chapter22_gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import static chapter22_gui.SwingConsole.run;

public class FileChooseTest extends JFrame {
	private JTextField fileName = new JTextField(), dir = new JTextField();
	private JButton open = new JButton("open"), save = new JButton("save");

	public FileChooseTest() {
		JPanel p = new JPanel();
		open.addActionListener(new OpenL());
		p.add(open);
		save.addActionListener(new SaveL());
		p.add(save);
		add(p, BorderLayout.SOUTH);
		dir.setEditable(false);
		fileName.setEditable(false);
		p = new JPanel();
		p.setLayout(new GridLayout(2, 1));
		p.add(fileName);
		p.add(dir);
		add(p, BorderLayout.NORTH);

	}

	class OpenL implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser c = new JFileChooser();
			int rVal = c.showOpenDialog(FileChooseTest.this);
			if (rVal == JFileChooser.APPROVE_OPTION) {
				fileName.setText(c.getSelectedFile().getName());
				dir.setText(c.getCurrentDirectory().toString());

			}
			if (rVal == JFileChooser.CANCEL_OPTION) {
				fileName.setText("You pressed cancel");
				dir.setText("");
			}
		}

	}

	class SaveL implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser c = new JFileChooser();
			int rVal = c.showOpenDialog(FileChooseTest.this);
			if (rVal == JFileChooser.APPROVE_OPTION) {
				fileName.setText(c.getSelectedFile().getName());
				dir.setText(c.getCurrentDirectory().toString());

			}
			if (rVal == JFileChooser.CANCEL_OPTION) {
				fileName.setText("You pressed cancel");
				dir.setText("");
			}

		}

	}

	public static void main(String[] args) {
		run(new FileChooseTest(), 250, 150);
	}

}
