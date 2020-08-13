package project;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class dialog {
	JFrame dialogFrame;

	public dialog() {
		dialogFrame = new JFrame("Dialog");
		dialogFrame.setSize(420, 440);
		dialogFrame.setLocationRelativeTo(null);
		dialogFrame.setVisible(true);
		





		JTextArea message = new JTextArea();
		message.setEditable(false);
		

		File dialog = new File("message.txt");
		try {
			BufferedReader buffDialog = new BufferedReader(new FileReader(dialog));
			String line = buffDialog.readLine();
			while (line != null) {
				message.setText(message.getText() + "\n" + line);
				line = buffDialog.readLine();
			}

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JButton close = new JButton("Close");
		close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dialogFrame.dispose();
			}
		});

		dialogFrame.add(message);
		dialogFrame.add(close, BorderLayout.SOUTH);

	}

}
