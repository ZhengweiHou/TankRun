package MyTankGame1_12;

import javax.swing.JFrame;

public class MyTankGameFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8063662427809298490L;
	MyPanel my = null;
	Hero hero  = null;

	// ¹¹Ôìº¯Êý
	public MyTankGameFrame() {
		this.hero = new Hero(200, 200); 
		this.my = new MyPanel(hero);
		this.add(my);
		this.addKeyListener(hero);
		this.setSize(my.getWidth() + 16, my.getHeight() + 39);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
