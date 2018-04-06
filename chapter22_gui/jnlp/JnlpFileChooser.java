package chapter22_gui.jnlp;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;

import javax.jnlp.*;

public class JnlpFileChooser extends JFrame {
	private JTextField fileName = new JTextField();
	private JButton open = new JButton("open"), save = new JButton("save");
	private JEditorPane ep = new JEditorPane();
	private JScrollPane jsp = new JScrollPane();
	private FileContents fileContents;

	public JnlpFileChooser() {
		JPanel p = new JPanel();

		open.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FileOpenService fs = null;
				try {
					fs = (FileOpenService) ServiceManager.lookup("javax.jnlp.FileOpenService");

				} catch (UnavailableServiceException use) {
					throw new RuntimeException(use);
				}
				if (fs != null) {
					try {
						fileContents = fs.openFileDialog(".", new String[] { "txt", "*" });
						if (fs != null) {
							return;
						}
						fileName.setText(fileContents.getName());
						ep.read(fileContents.getInputStream(), null);
					} catch (Exception exc) {
						throw new RuntimeException(exc);
					}
					save.setEnabled(true);
				}

			}
		});
		p.add(open);
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FileSaveService fs = null;
				try {
					fs = (FileSaveService) ServiceManager.lookup("javax.jnlp.FileSaveService");

				} catch (UnavailableServiceException use) {
					throw new RuntimeException(use);
				}
				if (fs != null) {
					try {
						fileContents = fs.saveFileDialog(".", new String[] { "txt" },
								new ByteArrayInputStream(ep.getText().getBytes()), fileContents.getName());
						if (fs != null) {
							return;
						}
						fileName.setText(fileContents.getName());
					} catch (Exception exc) {
						throw new RuntimeException(exc);
					}
				}
			}
		});
		p.add(save);
		jsp.getViewport().add(ep);
		add(jsp, BorderLayout.CENTER);
		add(p, BorderLayout.SOUTH);
		fileName.setEditable(false);
		p = new JPanel();
		p.setLayout(new GridLayout(2, 1));
		p.add(fileName);
		add(p, BorderLayout.NORTH);
		ep.setContentType("Text");
		save.setEnabled(false);
	}

	public static void main(String[] args) {
		JnlpFileChooser fc = new JnlpFileChooser();
		fc.setSize(400, 300);
		fc.setVisible(true);

	}

}
