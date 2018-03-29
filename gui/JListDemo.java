package gui;

import static gui.SwingConsole.run;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class JListDemo extends JFrame {
	private String[] names = { "zhang", "han", "zhao", "jiang", "li" };
	private DefaultListModel litems = new DefaultListModel();
	private JList lst = new JList(litems);
	private JTextArea t = new JTextArea(names.length, 20);
	private int count = 0;
	private JButton b = new JButton("Add Item");
	private ActionListener bl = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (count < names.length) {
				litems.add(0, names[count++]);
			} else {
				b.setEnabled(false);
			}
		}
	};
	private ListSelectionListener ll = new ListSelectionListener() {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (e.getValueIsAdjusting())
				return;
			t.setText("");
			for (Object item : lst.getSelectedValues())
				t.append(item + "\n");

		}
	};

	public JListDemo() {
		t.setEditable(false);
		setLayout(new FlowLayout());
		Border brd = BorderFactory.createMatteBorder(1, 1, 2, 2, Color.BLACK);
		lst.setBorder(brd);
		t.setBorder(brd);
		for (int i = 0; i < 4; i++) {
			litems.addElement(names[count++]);
		}
		add(t);
		add(lst);
		add(b);
		lst.addListSelectionListener(ll);
		b.addActionListener(bl);
	}

	public static void main(String[] args) {
		run(new JListDemo(), 250, 375);
	}

}
