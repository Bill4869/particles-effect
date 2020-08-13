package project;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.font.TextAttribute;
import java.awt.geom.Line2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class DPanel extends JPanel implements Runnable {
	List<Dot> dots;


	Graphics2D g2;

	AttributedString as;
	String newWord = "";
	String shortCut = "";

	String findthis = "";

	String mouseStatus = "";

	Color[] colorPalette;

	String bgStatus = "";

	List<String> yourLanguage = new ArrayList<>();

	int dotQuantity = 160;
	int radious = 100;
	int dotSize = 7;
	static int  n = 0;
	public DPanel() {
		super();
		setBackground(Color.BLACK);
		dots = new ArrayList<>();

		File setting = new File("Dot謨ｰ-蜊雁ｾ�-Dot縺ｮ繧ｵ繧､繧ｺ.txt");
		try {
			BufferedReader settBuff = new BufferedReader(new FileReader(setting));
			String line = settBuff.readLine();
			String[] line2 =  line.split("%:%");
			dotQuantity = Integer.parseInt(line2[0]);
			radious = Integer.parseInt(line2[1]);
			dotSize = Integer.parseInt(line2[2]);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<person> people = readFile.getPerson();
		for (person person : people) {
			Dot dot = new Dot((int)Math.ceil(Math.random()*1360), (int)Math.ceil(Math.random()*740), dotSize);
			dot.dotData(person);
			dot.setVector();
			dots.add(dot);
		}

		List<language> languages = readFile.getLanguages();
		for (language language : languages) {
			Dot dot = new Dot((int)Math.ceil(Math.random()*1360), (int)Math.ceil(Math.random()*740), dotSize);
			dot.dotData(language);
			dot.setVector();
			dots.add(dot);




		}
		for(int i = 0; i < dotQuantity; i++) {//60
			int numx = (int)Math.ceil(Math.random()*1360);
			int numy = (int)Math.ceil(Math.random()*740);
			Dot dot = new Dot(numx, numy, dotSize);
			dot.setVector();
			dots.add(dot);
		}


		addKeyListener(new searchLetter() );
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);


		colorPalette = new Color[2275];
	    for(int i = 0; i < 2275; i++)
	    {
	        colorPalette[i] = Color.getHSBColor((float) i / (float) 2275, 0.85f, 1.0f);
	    }




		Thread t = new Thread(this);
		t.start();

	}

	class searchLetter implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

			if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
				newWord = newWord.substring(0, newWord.length()-1);
			}
			else if (e.getKeyChar() == KeyEvent.VK_ESCAPE) {
				newWord = "";
			}
			else if (e.getKeyChar() == KeyEvent.VK_TAB) {
				if (shortCut != "") {
					newWord = shortCut;
				}
			}


			else {
				newWord += Character.toString(e.getKeyChar());

			}

		}

		@Override
		public void keyPressed(KeyEvent e) {


		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}


	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for (Dot dot : dots) {
			if (bgStatus.equals("white")) {
				g.setColor(Color.BLACK);
			}else {
				g.setColor(Color.WHITE);
			}
			g.fillOval(dot.x, dot.y, dot.SIZE, dot.SIZE);



		}
		if ( mouseStatus.equals("pressed")) {
			int x = 0;
			int y = 0;
			n = 0;

			while ( x <= 1344 || y <= 693) {
				if ( x > 1344) {
					x = 0;
					y = y + 21;
				}
				g.setColor(colorPalette[n]);
				g.fillRect(x , y + 5, 20, 20);
				x = x + 21;
				n++;
			}
		}


	}




	@Override
	protected void paintComponent(Graphics g) {
		// TODO 閾ｪ蜍慕函謌舌＆繧後◆繝｡繧ｽ繝�繝峨�ｻ繧ｹ繧ｿ繝�
		super.paintComponent(g);
		for (Dot dot : dots) {
			if (dot.x < 0 ) {
				dot.vector.x =  (int)Math.ceil(Math.random()*2);// 1330
			}
			else if (dot.x + (dotSize/2) > super.getWidth()) { // 1360
				dot.vector.x =  -(int)Math.ceil(Math.random()*2);
			}
			if (dot.y < 0 ) {
				dot.vector.y =  (int)Math.ceil(Math.random()*2);//635
			}
			else if (dot.y + (dotSize/2) > super.getHeight()) { // 740
				dot.vector.y =  -(int)Math.ceil(Math.random()*2);
			}
			dot.x += dot.vector.x;
			dot.y += dot.vector.y;

		}
		for (Dot dot : dots) {
			for (Dot dot2 : dots) {
				if ((dot.distance(dot, dot2)) < radious) {
					g2 = (Graphics2D) g;
					int distances = dot.distance(dot, dot2);
					if (bgStatus.equals("white")) {
						g2.setColor(Color.BLACK);
					}else {
						g2.setColor(Color.WHITE);
					}
					g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,(float)dot.stroke(distances, radious)));
					g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	                g2.draw(new Line2D.Float(dot.x + (dotSize/2), dot.y +(dotSize/2), dot2.x +(dotSize/2), dot2.y + (dotSize/2)));

				}

			}
		}
		for (int i = 0; i < dots.size(); i++) {
			Dot dot = dots.get(i);
			if (dot.data != null) {
				if (yourLanguage.isEmpty()) {
				}else {
					for (String urlanguage : yourLanguage) {
						if (dot.data.name.equals(urlanguage)) {
							as = new AttributedString(dot.data.name);
							as.addAttribute(TextAttribute.FOREGROUND, new Color(153, 0, 0 ), 0, dot.data.name.length());//(220, 20, 60
							as.addAttribute(TextAttribute.SIZE, 15);
							as.addAttribute(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
							g.drawString(as.getIterator(), dot.x + 4, dot.y - 4);
						}
					}
				}
				if (findthis != "") {
					switch (findthis) {
					case "Teacher" :
					case "Student" :
						if ( dot.data.toString().substring(0, findthis.length()).equals(findthis)) {
							as = new AttributedString(dot.data.name);
							as.addAttribute(TextAttribute.FOREGROUND, new Color(153, 0, 0 ), 0, dot.data.name.length());//(220, 20, 60
							as.addAttribute(TextAttribute.SIZE, 15);
							as.addAttribute(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
							g.drawString(as.getIterator(), dot.x + 4, dot.y - 4);


						}
						break;
					default:
						if (dot.data.toString().substring(0, 7).equals("Teacher") || dot.data.toString().substring(0, 7).equals("Student")) {
						}
						else {
							as = new AttributedString(dot.data.name);
							as.addAttribute(TextAttribute.FOREGROUND, new Color(153, 0, 0), 0, dot.data.name.length());
							as.addAttribute(TextAttribute.SIZE, 15);
							as.addAttribute(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
							g.drawString(as.getIterator(), dot.x + 4, dot.y - 4);
						}

						break;
					}
				}
				if (newWord.length() > 0 ) {
					if ( dot.data.name.length() >= newWord.length()) {
						if (dot.data.name.substring(0, newWord.length()).equals(newWord)) {
							findthis = "";
							as = new AttributedString(dot.data.name);
							if (newWord.length() == dot.data.name.length() - 1) {
								shortCut = dot.data.name;
							}else {shortCut = "";}
							if (dot.data.name.length() == newWord.length()) {
								as.addAttribute(TextAttribute.INPUT_METHOD_UNDERLINE, TextAttribute.UNDERLINE_LOW_TWO_PIXEL);

							}
							as.addAttribute(TextAttribute.FOREGROUND, new Color(72, 61, 139), 0, dot.data.name.length());//72, 61, 139
							as.addAttribute(TextAttribute.FOREGROUND, new Color(153, 0, 0), 0, newWord.length());
							as.addAttribute(TextAttribute.SIZE, 15);
							as.addAttribute(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
							g.drawString(as.getIterator(), dot.x + 4, dot.y - 4);


						}



					}
					if (dot.data.name.length() == newWord.length()) {
						if (dot.data.name.equals(newWord)) {
							g.drawString(as.getIterator(), dot.x + 4, dot.y - 4);
							AttributedString attribute = new AttributedString(dot.data.toString());
							attribute.addAttribute(TextAttribute.FOREGROUND, new Color(153, 0, 153));//139, 0, 139
							attribute.addAttribute(TextAttribute.SIZE, 13);
							g.drawString(attribute.getIterator(), dot.x + 10, dot.y + 15);
							dot.x = super.getWidth() / 2;
							dot.y = super.getHeight() / 2;
							dot.vector.x = 0;
							dot.vector.y = 0;

							if (yourLanguage.isEmpty()) {
								String[] yourLanguages = dot.data.toString().substring(10).split(",");
								for (int j = 0; j < yourLanguages.length; j++) {
									yourLanguage.add(yourLanguages[j]);

								}

							}
						}

					}

				}
				if (dot.vector.x == 0 && dot.vector.y == 0 && newWord.length() != dot.data.name.length()) {
					dot.setVector();
					yourLanguage.removeAll(yourLanguage);
				}
			}
		}
	}





	@Override
	public void run() {
		while (true) {
			repaint();
			try {
				Thread.sleep(60);//85, 35
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


}
