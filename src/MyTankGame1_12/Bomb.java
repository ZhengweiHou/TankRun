package MyTankGame1_12;

import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;

public class Bomb {
	int life = 18;
	int x;
	int y;
	Image image1;
	Image image2;
	Image image3;
	Image image4;
	Image image5;
	Image image6;

	public Bomb(int x, int y) {
		this.x = x;
		this.y = y;
		image1 = Toolkit.getDefaultToolkit().getImage(
				Panel.class.getResource("/boome1.png"));
		image2 = Toolkit.getDefaultToolkit().getImage(
				Panel.class.getResource("/boome2.png"));
		image3 = Toolkit.getDefaultToolkit().getImage(
				Panel.class.getResource("/boome3.png"));
		image4 = Toolkit.getDefaultToolkit().getImage(
				Panel.class.getResource("/boome4.png"));
		image5 = Toolkit.getDefaultToolkit().getImage(
				Panel.class.getResource("/boome5.png"));
		image6 = Toolkit.getDefaultToolkit().getImage(
				Panel.class.getResource("/boome6.png"));
	}

	public Image getImage() {
		if (life <= 3)
			return image1;
		else if (life <= 6)
			return image2;
		else if (life <= 9)
			return image3;
		else if (life <= 12)
			return image4;
		else if (life <= 15)
			return image5;
		else if (life <= 18)
			return image6;
		else
			return null;
	}
}
