package chapter22_gui;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

import static chapter22_gui.SwingConsole.run;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

public class Menus extends JFrame {
	private String[] flavors = { "zhang", "han", "zhao", "jiang", "li" };
	private JTextField t = new JTextField("No flavor", 30);
	private JMenuBar mb1 = new JMenuBar();
	private JMenu f = new JMenu("File"), m = new JMenu("Flavors"), s = new JMenu("Safety");
	private JCheckBoxMenuItem[] safety = { new JCheckBoxMenuItem("Guard"), new JCheckBoxMenuItem("Hide") };
	private JMenuItem[] file = { new JMenuItem("Open") };

	private JMenuBar mb2 = new JMenuBar();
	private JMenu foobar = new JMenu("fooBar");
	private JMenuItem[] others = { new JMenuItem("Foo", KeyEvent.VK_F), new JMenuItem("Bar", KeyEvent.VK_A),
			new JMenuItem("Baz"), };
	private JButton b = new JButton("Swap Menus");

	class BL implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JMenuBar m = getJMenuBar();
			setJMenuBar(m == mb1 ? mb2 : mb1);
			validate();

		}

	}

	class ML implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JMenuItem target = (JMenuItem) e.getSource();
			String actionCommand = target.getActionCommand();
			if (actionCommand.equals("Open")) {
				String s = t.getText();
				boolean chosen = false;
				for (String flavor : flavors) {
					if (s.equals(flavor))
						chosen = true;
					if (!chosen)
						t.setText("Choose a flavor first!");
					else
						t.setText("Opening " + s + ". Mmm, mm!");
				}

			}

		}
	}

	class FL implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JMenuItem target = (JMenuItem) e.getSource();
			t.setText(target.getText());
		}

	}

	class FooL implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			t.setText("Foo selected");
		}

	}

	class BarL implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			t.setText("Bar selected");
		}

	}

	class BazL implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			t.setText("Baz selected");
		}

	}

	class CMIL implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			JCheckBoxMenuItem target = (JCheckBoxMenuItem) e.getSource();
			String actionCommand = target.getActionCommand();
			if (actionCommand.equals("Guard")) {
				t.setText("Guard the Ice Cream! Guarding is " + target.getState());
			} else if (actionCommand.equals("Guard")) {
				t.setText("Hide the Ice Cream! Is it hidden? " + target.getState());
			}

		}

	}

	public Menus() {
		ML ml = new ML();
		CMIL cmil = new CMIL();
		safety[0].setActionCommand("Guard");
		safety[0].setMnemonic(KeyEvent.VK_G);
		safety[0].addItemListener(cmil);

		safety[1].setActionCommand("Hide");
		safety[1].setMnemonic(KeyEvent.VK_H);
		safety[1].addItemListener(cmil);

		others[0].addActionListener(new FooL());
		others[1].addActionListener(new BarL());
		others[2].addActionListener(new BazL());

		FL fl = new FL();
		int n = 0;
		for (String flavor : flavors) {
			JMenuItem mi = new JMenuItem(flavor);
			mi.addActionListener(fl);
			m.add(mi);
			if ((n++ + 1) % 3 == 0) {
				m.addSeparator();
			}
		}
		for (JCheckBoxMenuItem sfty : safety) {
			s.add(sfty);
		}
		s.setMnemonic(KeyEvent.VK_A);
		f.add(s);
		f.setMnemonic(KeyEvent.VK_F);
		for (int i = 0; i < file.length; i++) {
			file[i].addActionListener(ml);
			f.add(file[i]);
		}
		mb1.add(f);
		mb1.add(m);
		setJMenuBar(mb1);
		t.setEditable(false);
		add(t, BorderLayout.CENTER);
		b.addActionListener(new BL());
		b.setMnemonic(KeyEvent.VK_S);
		add(b, BorderLayout.NORTH);
		for (JMenuItem oth : others) {
			foobar.add(oth);
		}
		foobar.setMnemonic(KeyEvent.VK_B);
		mb2.add(foobar);

	}

	public static void main(String[] args) {
		run(new Menus(), 300, 200);
	}

}
