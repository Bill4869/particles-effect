package project;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
	public static void main(String[] args) {
		DFrame frame = new DFrame();
//		frame.setVisible(true);
		
		GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		frame.menuBar.fullScreen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				frame.setUndecorated(true);
				frame.setVisible(true);
				device.setFullScreenWindow(frame);
				
				
			}
		});
		frame.menuBar.normalScreen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				frame.setUndecorated(false);
				frame.setVisible(true);
				
			}
		});

		


	}
}
