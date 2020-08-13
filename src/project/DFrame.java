package project;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

public class DFrame extends JFrame{
	DPanel main = new DPanel();

	JFrame clr;
	JTextField red;
	JTextField green;
	JTextField blue;

	MenuBar menuBar;

	public DFrame() {
		super("Stars");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		setExtendedState(JFrame.MAXIMIZED_BOTH); 
//		setUndecorated(true);
		setSize(1300, 700);
		setLocationRelativeTo(null);

		ImageIcon image = new ImageIcon("moon.jpg");
		Image img = image.getImage();
		Image img2 = img.getScaledInstance(20, 20, 20);
		setIconImage(img2);






		JPanel panel = (JPanel)getContentPane();


		menuBar = new MenuBar();
		panel.add(menuBar, BorderLayout.NORTH);
		menuBar.quit.addActionListener(new QuitAction());
		menuBar.redColor.addActionListener(new changeColor(menuBar.redColor.getBackground()));
		menuBar.blackColor.addActionListener(new changeColor(menuBar.blackColor.getBackground()));
		menuBar.blueColor.addActionListener(new changeColor(menuBar.blueColor.getBackground()));
		menuBar.cyanColor.addActionListener(new changeColor(menuBar.cyanColor.getBackground()));
		menuBar.darkGrayColor.addActionListener(new changeColor(menuBar.darkGrayColor.getBackground()));
		menuBar.grayColor.addActionListener(new changeColor(menuBar.grayColor.getBackground()));
		menuBar.greenColor.addActionListener(new changeColor(menuBar.greenColor.getBackground()));
		menuBar.lightGrayColor.addActionListener(new changeColor(menuBar.lightGrayColor.getBackground()));
		menuBar.magentaColor.addActionListener(new changeColor(menuBar.magentaColor.getBackground()));
		menuBar.orangeColor.addActionListener(new changeColor(menuBar.orangeColor.getBackground()));
		menuBar.pinkColor.addActionListener(new changeColor(menuBar.pinkColor.getBackground()));
		menuBar.whiteColor.addActionListener(new changeColor(menuBar.whiteColor.getBackground()));
		menuBar.yellowColor.addActionListener(new changeColor(menuBar.yellowColor.getBackground()));

		menuBar.others.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clr = new JFrame("Color customizing");
				clr.setSize(new Dimension(400, 500));
				clr.setVisible(true);
				clr.setLayout(null);

				JLabel detail = new JLabel("謨ｰ蛟､繧貞�･蜉帙＠縺ｦ縺上□縺輔＞�ｼ茨ｼ撰ｽ橸ｼ抵ｼ包ｼ包ｼ�");
				detail.setBounds(75, 70, 400, 20);
				JLabel redLabel = new JLabel("RED");
				redLabel.setBounds(64, 160, 50, 20);
				JLabel greenLabel = new JLabel("GREEN");
				greenLabel.setBounds(171, 160, 70, 20);
				JLabel blueLabel = new JLabel("BLUE");
				blueLabel.setBounds(292, 160, 50, 20);

				clr.add(detail);
				clr.add(redLabel);
				clr.add(greenLabel);
				clr.add(blueLabel);



				red = new JTextField();
				red.setBounds(50, 180, 50, 50);
				red.addKeyListener(new keylisten());
				red.setHorizontalAlignment(JTextField.CENTER);
				green = new JTextField();
				green.setBounds(165, 180, 50, 50);
				green.setHorizontalAlignment(JTextField.CENTER);
				green.addKeyListener(new keylisten());
				blue = new JTextField();
				blue.setBounds(280, 180, 50, 50);
				blue.setHorizontalAlignment(JTextField.CENTER);
				blue.addKeyListener(new keylisten());
				clr.add(red);
				clr.add(green);
				clr.add(blue);

				JButton enter = new JButton("OK");
				enter.setBounds(140, 270, 100, 40);

				clr.add(enter);
				enter.addActionListener(new RGBchanges());



			}
		});


		menuBar.teacher.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, KeyEvent.CTRL_DOWN_MASK));
		menuBar.student.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
		menuBar.language.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.CTRL_DOWN_MASK));
		menuBar.teacher.addActionListener(new findSTH("Teacher"));
		menuBar.student.addActionListener(new findSTH("Student"));
		menuBar.language.addActionListener(new findSTH("Language"));










		panel.add(main);
		setVisible(true);

		main.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				if ( main.mouseStatus.equals("pressed")) {
					main.mouseStatus = "released";

				}
				if ( main.mouseStatus.equals("released")) {
					try {
						Robot robot = new Robot();
						Rectangle rec = new Rectangle(MouseInfo.getPointerInfo().getLocation().x, MouseInfo.getPointerInfo().getLocation().y, 2, 2);
						BufferedImage buffImage = robot.createScreenCapture(rec);
						int clr = buffImage.getRGB(1, 1);
						int red = (clr >> 16) & 0xFF;
						int green = (clr >> 8) & 0xFF;
						int blue = clr & 0xFF;
						main.setBackground(new Color(red, green, blue));
						main.mouseStatus = "";

					} catch (AWTException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)) {
					main.mouseStatus = "pressed";

				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});





	}
	class findSTH implements ActionListener {
		String keyWord = "";
		public findSTH(String keyWord) {
			this.keyWord = keyWord;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			main.findthis = keyWord;
			}

	}

	void changeRGB () {
		main.newWord = "";
//		clr.dispose();
		main.setBackground(new Color(Integer.parseInt(red.getText()),
				Integer.parseInt(green.getText()), Integer.parseInt(blue.getText())));

	}

	class keylisten implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyChar() == KeyEvent.VK_ENTER) {
				changeRGB();
			}

		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

	}

	class RGBchanges implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			changeRGB();
		}

	}

	class changeColor implements ActionListener {
		Color paneColor;
		public changeColor(Color color) {
			this.paneColor = color;


		}

		@Override
		public void actionPerformed(ActionEvent e) {
			main.setBackground(paneColor);
			main.newWord = "";
			if ( paneColor == Color.WHITE) {
				main.bgStatus = "white";
			}
			else {
				main.bgStatus = "";
			}
		}

	}

	class QuitAction extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);

		}

	}




}