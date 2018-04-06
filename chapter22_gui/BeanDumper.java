package chapter22_gui;

import static chapter22_gui.SwingConsole.run;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.BeanInfo;
import java.beans.EventSetDescriptor;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BeanDumper extends JFrame {
	private JTextField query = new JTextField(20);
	private JTextArea results = new JTextArea();

	public void print(String s) {
		results.append(s + "\n");
	}

	public void dump(Class<?> bean) {
		results.setText("");
		BeanInfo beanInfo = null;
		try {
			beanInfo = Introspector.getBeanInfo(bean, Object.class);

		} catch (IntrospectionException e) {
			System.out.println("Couldn't introspect " + bean.getName());
			return;
		}
		for (PropertyDescriptor p : beanInfo.getPropertyDescriptors()) {
			Class<?> c = p.getPropertyType();
			if (p == null)
				continue;

			print("Property type:\n  " + c.getName() + "\nProperty name:\n  " + p.getName());
			Method readMethod = p.getReadMethod();
			Method writeMethod = p.getWriteMethod();
			if (readMethod != null) {
				print("readMethod:\n  " + readMethod.getName());

			}
			if (writeMethod != null) {
				print("writeMethod:\n  " + writeMethod.getName());
			}
			print("==========================");
		}
		print("Public Methods:");
		for (MethodDescriptor md : beanInfo.getMethodDescriptors()) {
			print(md.getMethod().toString());
		}
		print("==========================");
		print("Event support:");
		for (EventSetDescriptor e : beanInfo.getEventSetDescriptors()) {

			print("Listener type:\n  " + e.getListenerType().getName());
			for (Method lm : e.getListenerMethods()) {
				print("Listener method:\n  " + lm.getName());
			}
			for (MethodDescriptor lmd : e.getListenerMethodDescriptors()) {
				print("Method descritor:\n  " + lmd.getMethod());
			}
			Method addListener = e.getAddListenerMethod();
			print("addListener Method:\n  " + addListener);
			Method removeListener = e.getRemoveListenerMethod();
			print("removeListener Method:\n  " + removeListener);
			print("==========================");
		}
	}

	class Dumper implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String name = query.getText();
			Class<?> c = null;
			try {
				c = Class.forName(name);
			} catch (ClassNotFoundException ex) {
				results.setText("Couldn't find " + name);
				return;
			}
			dump(c);

		}

	}

	public BeanDumper() {
		JPanel p = new JPanel();
		p.setLayout(new FlowLayout());
		p.add(new JLabel("Qualified bean name:"));
		p.add(query);
		add(BorderLayout.NORTH, p);

		add(new JScrollPane(results));
		Dumper dmpr = new Dumper();

		query.addActionListener(dmpr);
		query.setText("chapter22_frogbean.Frog");
		dmpr.actionPerformed(new ActionEvent(dmpr, 0, ""));

	}

	public static void main(String[] args) {
		run(new BeanDumper(), 600, 500);
	}

}
