package chapter22_gui;

import static chapter22_gui.SwingConsole.run;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

public class Borders extends JFrame {
	static JPanel showBorder(Border border) {
		JPanel jp = new JPanel();
		jp.setLayout(new BorderLayout());
		String name = border.getClass().getSimpleName();
		jp.add(new JLabel(name, JLabel.CENTER), BorderLayout.CENTER);
		jp.setBorder(border);
		jp.setBorder(border);
		return jp;
	}

	public Borders() {
		setLayout(new GridLayout(2, 4));
		add(showBorder(new TitledBorder("Title")));
		add(showBorder(new EtchedBorder()));
		add(showBorder(new LineBorder(Color.BLUE)));
		add(showBorder(new MatteBorder(5, 5, 30, 30, Color.GREEN)));
		add(showBorder(new BevelBorder(BevelBorder.RAISED)));
		add(showBorder(new SoftBevelBorder(BevelBorder.LOWERED)));
		add(showBorder(new CompoundBorder(new EtchedBorder(), new LineBorder(Color.RED))));

	}

	public static void main(String[] args) {
		run(new Borders(), 500, 300);
	}

}
