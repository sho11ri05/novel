package game.hirois;

import game.GraphicsInfo;
import game.hirois.BulletCharaAbstract;

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
	public abstract ArrayList<BulletCharaAbstract> getBullet();


	//弾の画像を取得する
	public abstract BufferedImage getBulletImage(int type);

	//空いている雪を探す
	protected int searchidx = 0;
	public BulletCharaAbstract searchBullet(){
		ArrayList<BulletCharaAbstract> bullet = this.getBullet();
		int m = bullet.size();

		//雪を探す
		for(;this.searchidx < m; this.searchidx++){
			if(bullet.get(this.searchidx).visible == false) {
				return bullet.get(this.searchidx);
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
