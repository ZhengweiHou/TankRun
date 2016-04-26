package MyTankGame1_12;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.util.Vector;

import javax.swing.JPanel;

/**
 * @Description 此类中将坦克大战panel中的方法封装起来
 */
public class TankUtil {
	/**
	 * @author HZW
	 * @Description 画出坦克的函数(坦克，画笔）
	 */

	public void drawTank(Tank tank, Graphics g) {
		// 判断坦克的类型
		switch (tank.getType()) {
		case 0:
			g.setColor(Color.cyan);
			break;
		case 1:
			g.setColor(Color.yellow);
			break;
		default:
			break;
		}
		tank.runTo(g);
	}

	/**
	 * @author HZW_922
	 * @Description 画出子弹
	 */
	public void drawShot(Vector<Shot> shotBox, Graphics g) {
		
		for (int i = 0; i < shotBox.size(); i++) {
			Shot myShot = shotBox.get(i);
			if (myShot != null && myShot.isLive == true) {
				switch (myShot.getType()) {
				case 0:
					g.setColor(Color.cyan);
					break;
				case 1:
					g.setColor(Color.yellow);
					break;
				default:
					break;
				}
				// g.fill3DRect(myShot.getX(), myShot.getY(), 3, 3, false);
				g.fillOval(myShot.getX(), myShot.getY(), 5, 5);
			}
		}
	}

	/**
	 * @author HZW_922
	 * @Description 判定坦克体积，是否能向前移动
	 */
	public void tankCanRun(Vector<Tank> tankList) {
		for (int i = 0; i < tankList.size(); i++) {
			Tank tank = tankList.get(i);
			int lengh = 30;
			switch (tank.direct) {
			case 0:
				tank.canUp = true;
				for (int j = 0; j < tankList.size(); j++) {
					Tank tank2 = tankList.get(j);
					if (i != j && tank.x < tank2.x + 30
							&& tank.x + 30 > tank2.x && tank.y > tank2.y) {
						if ((tank.wantUp() - tank2.y) < lengh)
							lengh = (tank.wantUp() - tank2.y);
					}
				}
				if (lengh < 30)
					tank.canUp = false;
				break;
			case 1:
				tank.canDown = true;
				for (int j = 0; j < tankList.size(); j++) {
					Tank tank2 = tankList.get(j);
					if (i != j && tank.x < tank2.x + 30
							&& tank.x > tank2.x - 30 && tank.y < tank2.y) {
						if ((tank2.y - tank.wantDown()) < lengh)
							lengh = (tank2.y - tank.wantDown());
					}
				}
				if (lengh < 30)
					tank.canDown = false;
				break;
			case 2:
				tank.canLeft = true;
				for (int j = 0; j < tankList.size(); j++) {
					Tank tank2 = tankList.get(j);
					if (i != j && tank.y + 30 > tank2.y
							&& tank.y < tank2.y + 30 && tank.x > tank2.x) {
						if ((tank.wantLeft() - tank2.x) < lengh)
							lengh = (tank.wantLeft() - tank2.x);
					}
				}
				if (lengh < 30)
					tank.canLeft = false;
				break;
			case 3:
				tank.canRight = true;
				for (int j = 0; j < tankList.size(); j++) {
					Tank tank2 = tankList.get(j);
					if (i != j && tank.y + 30 > tank2.y
							&& tank.y < tank2.y + 30 && tank.x < tank2.x) {
						if ((tank2.x - tank.wantRight()) < lengh)
							lengh = (tank2.x - tank.wantRight());
					}
				}
				if (lengh < 30)
					tank.canRight = false;
				break;

			default:
				break;
			}

		}

		// for (int j = 0; j < tankList.size(); j++) {
		// Tank tank2 = tankList.get(j);
		// if (i != j) {
		// switch (tank.direct) {
		// case 0:
		// if (tank.wantUp() < tank2.y + 30
		// && tank.wantUp() > tank2.y-30
		// && tank.x < tank2.x + 30
		// && tank.x > tank2.x - 30)
		// tank.canUp = false;
		// else
		// tank.canUp = true;
		// break;
		// case 1:
		// if (tank.wantDown() > tank2.y-30
		// && tank.wantDown() < tank2.y + 30
		// && tank.x < tank2.x + 30
		// && tank.x > tank2.x - 30)
		// tank.canDown = false;
		// else
		// tank.canDown = true;
		// break;
		// case 2:
		// if (tank.wantLeft() < tank2.x + 30
		// && tank.wantLeft() > tank2.x-30
		// && tank.y < tank2.y + 30
		// && tank.y > tank2.y - 30)
		// tank.canLeft = false;
		// else
		// tank.canLeft = true;
		// break;
		// case 3:
		// if (tank.wantRight() > tank2.x-30
		// && tank.wantLeft() < tank2.x + 30
		// && tank.y < tank2.y + 30
		// && tank.y > tank2.y - 30)
		// tank.canRight = false;
		// else
		// tank.canRight = true;
		// break;
		//
		// default:
		// break;
		// }
		// }
		// }
	}
}
