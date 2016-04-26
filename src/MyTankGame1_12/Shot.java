package MyTankGame1_12;

/*
 * 子弹类
 */
public class Shot implements Runnable {
	private int x = 0;// 表示子弹的横坐标
	private int y = 0;// 表示子弹的纵坐标
	private int direct = 0;// 子弹的方向
	private int type;// 子弹的类型
	private int speed;// 子弹的速度
	boolean isLive ;

	public Shot(int x, int y, int direct, int type, int speed) {
		super();
		this.direct = direct;
		this.type = type;
		this.speed = speed;
		isLive = true;
		switch (direct) {
		case 0:
			this.x = x + 15 - 2;
			this.y = y-2;
			break;
		case 1:

			this.x = x + 15 - 2;
			this.y = y + 30+2;
			break;
		case 2:

			this.x = x-2;
			this.y = y + 15 - 2;
			break;
		case 3:
			this.x = x + 30+2;
			this.y = y + 15 - 2;
			break;
		default:
			break;
		}
	}

	public void run() {
		while (true) {
			switch (direct) {
			case 0:
				y -= speed;
				break;
			case 1:
				y += speed;
				break;
			case 2:
				x -= speed;
				break;
			case 3:
				x += speed;
				break;
			default:
				break;
			}
			try {
				Thread.sleep(60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (x < 0 || y < 0 || x > 500 || y > 500)
			{
				this.isLive = false;
				
			}
			if(this.isLive == false)
				break;
		}

	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getDirect() {
		return direct;
	}

	public void setDirect(int direct) {
		this.direct = direct;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

}
