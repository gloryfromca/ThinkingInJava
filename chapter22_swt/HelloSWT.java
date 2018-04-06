package chapter22_swt;

import org.eclipse.swt.widgets.*;

public class HelloSWT {
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("Hi there, SWT!"); // Title bar
		shell.open();
		while (!shell.isDisposed())
			if (!display.readAndDispatch())
				display.sleep();
		display.dispose();
	}
}
