package chapter22;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import static chapter22.SwingConsole.run;

public class ShowAddListeners extends JFrame {
	private JTextField name = new JTextField();
	private JTextArea results = new JTextArea(40, 65);
	private static Pattern addListener = Pattern.compile("(add\\w+?Listener\\(.*?\\))");
	private static Pattern qualifier = Pattern.compile("\\w+\\.");

	class NameListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String nm = name.getText().trim();
			if (nm.length() == 0) {
				results.setText("No match");
				return;
			}
			Class<?> kind;
			try {
				kind = Class.forName("javax.swing." + nm);
			} catch (ClassNotFoundException ex) {
				results.setText("No match");
				return;
			}
			Method[] methods = kind.getMethods();
			results.setText("");
			for (Method method : methods) {
				Matcher matcher = addListener.matcher(method.toString());
				if (matcher.find()) {
					System.out.println(matcher);
					System.out.println(matcher.group(0));
					// group 1 is the entire string (group 0) because the string is encompassed with
					// parentheses.
					System.out.println(matcher.group(1));
					System.out.println(qualifier.matcher(matcher.group(0)).replaceAll(""));
					System.out.println("=====");
					results.append(qualifier.matcher(matcher.group(1)).replaceAll("") + "\n");
				}

			}

		}

	}

	public ShowAddListeners() {
		NameListener nameListener = new NameListener();
		name.addActionListener(nameListener);
		JPanel top = new JPanel();
		top.add(new JLabel("Swing class name (press Enter):"));
		top.add(name);
		add(BorderLayout.NORTH, top);
		add(new JScrollPane(results));
		name.setText("JTextArea");
		nameListener.actionPerformed(new ActionEvent("", 0, ""));

	}

	public static void main(String[] args) {
		run(new ShowAddListeners(), 500, 400);
	}

}
