package game.hirois.hiroi2;

import game.GraphicsInfo;
import game.hirois.Shooter;
import game.hirois.Stage;
import game.hirois.Vector2D;

import java.awt.geom.Point2D;
import java.util.Random;

public class CrossShooter extends Shooter {

	private Vector2D v = new Vector2D();

	@Override
	public void shoot(GraphicsInfo ginfo, Stage stage, Point2D.Double position) {

        Random l = new Random();
		double r = 180.0 / 180.0 * Math.PI;  //弾の飛んでいく向き

//		double r = 90.0 / 180.0 * Math.PI;
		this.v.x = 0.0;
		this.v.y = 200.0;
		for(int i=0; i<1; i++){
			BulletChara bullet = (BulletChara) stage.searchBullet();
			if(bullet == null) return;
			bullet.mover = StraightMover.singleton;
			bullet.position.x = l.nextInt(780);          //乱数を取得する //position.x;
			bullet.position.y = 570;   //position.y;
			bullet.vector.x = this.v.x;
			bullet.vector.y = this.v.y;
//			bullet.setImage(stage.getBulletImage(Stage1.GREENBULLET_E));
			bullet.setImage(stage.getBulletImage(Stage1.INA));
			bullet.setVisible(ginfo, true);
			bullet.vector.rotateVector(r);
//			this.v.rotateVector(r);
		}

		for(int i=0; i<1; i++){
			BulletChara bullet = (BulletChara) stage.searchBullet();
			if(bullet == null) return;
			bullet.mover = StraightMover.singleton;
			bullet.position.x = l.nextInt(780);          //乱数を取得する //position.x;
			bullet.position.y = 570;   //position.y;
			bullet.vector.x = this.v.x;
			bullet.vector.y = this.v.y;
			bullet.setImage(stage.getBulletImage(Stage1.INA));
			bullet.setVisible(ginfo, true);
			bullet.vector.rotateVector(r);
//			this.v.rotateVector(r);
		}
	}

	public static CrossShooter singleton = new CrossShooter();

}
