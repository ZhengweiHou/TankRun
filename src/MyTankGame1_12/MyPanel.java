package MyTankGame1_12;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.JPanel;

//我的面板
class MyPanel extends JPanel implements Runnable {
	// 定义一个我的坦克
	TankUtil tankUtil = new TankUtil();
	Hero hero = null;

	Vector<Bomb> bombList = new Vector<Bomb>();
	Vector<Tank> tankList = new Vector<Tank>();
	Vector<Tank> EnemyList = new Vector<Tank>();
	// 存放所有弹夹的向量集合
	Vector<ShotBoxClass> shotBoxVector = new Vector<ShotBoxClass>();
	int width;
	int height;
	Vector<Tank> beBoomTankList = new Vector<Tank>();

	// 构造函数
	public MyPanel(Hero hero) {
		this.hero = hero;
		shotBoxVector.add(hero.shotBoxC);
		tankList.add(this.hero);
		this.width = 500;
		this.height = 500;

		for (int i = 1; i <= 3; i++) {
			Enemy en = new Enemy(100 * i, 50);
			shotBoxVector.add(en.shotBoxC);
			this.EnemyList.add(en);
		}
		Thread t = new Thread(this);
		t.setName("面板刷新");
		t.start();
	}

	// 重写paint函数
	public void paint(Graphics g) {
		super.paint(g);

		// ///////////////////
		// 生成敌方坦克
		if (EnemyList.size() < 10) {
			Enemy ene = new Enemy((int) (Math.random() * 470),
					(int) (Math.random() * 470));
			EnemyList.add(ene);

		}

		// 检查敌人坦克的状态
		Vector<Tank> deTank = new Vector<Tank>();
		for (Tank tank : EnemyList) {
			if (tank.live <= 0)
				deTank.add(tank);
		}
		EnemyList.removeAll(deTank);

		// 将坦克添加到坦克集合中
		tankList.clear();
		if (EnemyList.size() > 0)
			tankList.addAll(EnemyList);
		if (hero.live > 0)
			tankList.add(hero);

		// 判断坦克体积
		tankUtil.tankCanRun(tankList);

		// ***********************************
		// 删除空弹夹
		Vector<ShotBoxClass> delShotBoxVector = new Vector<ShotBoxClass>();
		for (ShotBoxClass shotBoxC : shotBoxVector) {
			if (shotBoxC.useShotBox == true&&shotBoxC.shotBox.size()<=0)
				delShotBoxVector.add(shotBoxC);
		}
		shotBoxVector.removeAll(delShotBoxVector);

		// 判断坦克是否发射了子弹，设置其弹夹状态
		for (Tank tank : tankList) {

			if (tank.shotBoxC.shotBox.size() >= 1 && tank.shotBoxC.useShotBox == false) {

				shotBoxVector.add(tank.shotBoxC);
				tank.shotBoxC.useShotBox = true;
			} else if (tank.shotBoxC.shotBox.size() < 1) {

				tank.shotBoxC.useShotBox = false;
			}
		}
		// ****************************************************

		// 判断子弹是否击中敌坦克，并处理坦克和子弹的状态,获得爆炸
		hitTank(shotBoxVector, tankList);

		for (Tank beBoomTank : beBoomTankList) {
			if (beBoomTank != null) {
				bombList.add(new Bomb(beBoomTank.getX(), beBoomTank.getY()));
			}
		}
		// //////////////////

		// 画出活动区域
		g.fillRect(0, 0, width, height);

		// 画出坦克
		for (Tank tank : tankList) {
			if (tank.live > 0)
				tankUtil.drawTank(tank, g);
		}

		// 画出所有子弹
		for (ShotBoxClass shotBoxC : shotBoxVector) {
			tankUtil.drawShot(shotBoxC.shotBox, g);
		}

		// 画出爆炸效果
		Vector<Bomb> deBombList = new Vector<Bomb>();
		for (Bomb bomb : bombList) {
			if (bomb.life > 0) {
				g.drawImage(bomb.getImage(), bomb.x, bomb.y, 40, 40, this);
				bomb.life--;
			} else if (bomb.life <= 0)
				deBombList.add(bomb);
		}
		bombList.removeAll(deBombList);

		g.setColor(Color.white);
		g.drawString("生命：" + hero.live, 10, 10);
		g.drawString("子弹：" + hero.shotBoxC.shotBox.size(), 10, 25);
		g.drawString("敌人：" + EnemyList.size(), 10, 40);
		g.drawString("射杀：" + hero.hitNum, 10, 55);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	// 面板的自动线程刷新
	@Override
	public void run() {
		// TODO Auto-generated method stub
		// 每隔50毫秒replain
		while (true) {
			try {
				Thread.sleep(55);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// 重新画图
			repaint();

		}

	}

	/**
	 * @author HZW_922
	 * @Description 判断我的子弹是否击中敌方坦克，并处理坦克和子弹的状态
	 */
	public void hitTank(Vector<ShotBoxClass> shotBoxVector,
			Vector<Tank> tankList) {
		beBoomTankList.clear();
		try {
			for (ShotBoxClass shotBoxC : shotBoxVector) {
				for (Shot shot : shotBoxC.shotBox) {
					for (Tank tank : tankList) {
						if (shot.getX() > tank.getX() - 1
								&& shot.getX() < tank.getX() + 31
								&& shot.getY() > tank.getY() - 1
								&& shot.getY() < tank.getY() + 31) {
							shot.isLive = false;
							shotBoxC.shotBox.remove(shot);

							if (shot.getType() != tank.getType()) {

								if (--tank.live <= 0) {
									if (tank.type == 0)
										hero.hitNum++;
									beBoomTankList.add(tank);
								}
							}
//							if (shotBoxC.shotBox.size() < 1) {
//								synchronized (this) {
//									shotBoxVector.remove(shotBoxC);
//								}
//							}

						}

					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
	}

}
