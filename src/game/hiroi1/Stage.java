package game.hiroi1;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Stage {

	private double width = 780;
	private double height = 580;

	public abstract GameChara getPlayer();
	public abstract void loadMedia() throws IOException;
	public abstract GameChara getEnemy();

	public abstract void draw(GraphicsInfo ginfo);
	public abstract void init(GraphicsInfo ginfo);


	//雪のデータを取得する
	public abstract ArrayList<BulletChara> getYuki1();


	//弾の画像を取得する
	public abstract BufferedImage getBulletImage(int type);

	//空いている雪を探す
	protected int searchidx = 0;
	public BulletChara searchBullet(){
		ArrayList<BulletChara> yuki1 = this.getYuki1();
		int m = yuki1.size();

		//雪を探す
		for(;this.searchidx < m; this.searchidx++){
			if(yuki1.get(this.searchidx).visible == false) {
				return yuki1.get(this.searchidx);
			}
		}
		//空きがないときはnullを返して発射キャンセル
		this.searchidx = 0;
		return null;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}

	public abstract boolean hitTestAll(GraphicsInfo ginfo);
	public abstract boolean isBossLiving();
	public void show(GraphicsInfo ginfo) {
		// TODO 自動生成されたメソッド・スタブ

	}
}
