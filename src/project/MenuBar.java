package project;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class MenuBar extends JMenuBar{
	JMenu app;
	JMenuItem appuri;
	JMenuItem quit;

	JMenu color;
	JMenuItem redColor;
	JMenuItem blueColor;
	JMenuItem blackColor;
	JMenuItem whiteColor;
	JMenuItem greenColor;
	JMenuItem grayColor;
	JMenuItem orangeColor;
	JMenuItem pinkColor;
	JMenuItem yellowColor;
	JMenuItem cyanColor;
	JMenuItem darkGrayColor;
	JMenuItem lightGrayColor;
	JMenuItem magentaColor;
	JMenuItem others;
	
	JMenuItem fullScreen;
	JMenuItem normalScreen;
	
	JMenuItem teacher;
	JMenuItem student;
	JMenuItem language;
	
	
	public MenuBar() {
		app = new JMenu("App");
		app.setMnemonic('A');

		appuri = new JMenuItem("Stars");
		app.add(appuri);
		appuri.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog Dialog = new dialog();
				JDialog dialog = new JDialog(Dialog.dialogFrame);
			}
		});

		app.addSeparator();

		quit = new JMenuItem("Quit", 'Q');
		quit.setAccelerator(KeyStroke.getKeyStroke('Q',KeyEvent.CTRL_DOWN_MASK));
		app.add(quit);
		add(app);

		color = new JMenu("Color");
		color.setMnemonic('C');
		add(color);





		redColor = new colorItem("Red", 'R', Color.RED);
		color.add(redColor);
		blueColor = new colorItem("Blue", 'B', Color.BLUE);
		color.add(blueColor);
		blackColor = new colorItem("Black", 'l', Color.BLACK);
		color.add(blackColor);
		whiteColor = new colorItem("White", 'W', Color.WHITE);
		color.add(whiteColor);
		greenColor = new colorItem("Green", 'G', Color.GREEN);
		color.add(greenColor);
		grayColor = new colorItem("Gray", 'A', Color.GRAY);
		color.add(grayColor);
		orangeColor = new colorItem("Orange", 'O', Color.ORANGE);
		color.add(orangeColor);
		pinkColor = new colorItem("Pink", 'P', Color.PINK);
		color.add(pinkColor);
		yellowColor = new colorItem("Yellow", 'Y', Color.YELLOW);
		color.add(yellowColor);
		cyanColor = new colorItem("Cyan", 'C', Color.CYAN);
		color.add(cyanColor);
		darkGrayColor = new colorItem("Dark gray", 'D', Color.DARK_GRAY);
		color.add(darkGrayColor);
		lightGrayColor = new colorItem("Light gray", 'i', Color.LIGHT_GRAY);
		color.add(lightGrayColor);
		magentaColor = new colorItem("Magenta", 'M', Color.MAGENTA);
		color.add(magentaColor);

		others = new JMenuItem("Others", 't');
		color.add(others);
		
		JMenu maxScreen = new JMenu("Windows");
		maxScreen.setMnemonic('W');
		add(maxScreen);
		
		fullScreen = new JMenuItem("Full Screen");
		maxScreen.add(fullScreen);
		normalScreen = new JMenuItem("Normal Screen");
		maxScreen.add(normalScreen);
		
		JMenu find = new JMenu("Find");
		find.setMnemonic('F');
		add(find);
		
		teacher = new JMenuItem("Teacher(s)");
		student = new JMenuItem("Student(s)");
		language = new JMenuItem("Language(s)");
		find.add(teacher);
		find.add(student);
		find.add(language);
		
		


	}

	public class colorItem extends JMenuItem {


		public colorItem(String name, char mnemonic, Color color) {
			if (color == Color.WHITE) {
				this.setForeground(Color.BLACK);
			}else {
				this.setForeground(Color.WHITE);

			}
			this.setText(name);
			this.setBackground(color);
			this.setMnemonic(mnemonic);


		}
	}

}
