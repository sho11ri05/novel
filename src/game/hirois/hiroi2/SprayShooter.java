package game.hirois.hiroi2;

import game.GraphicsInfo;
import game.hirois.Shooter;
import game.hirois.Stage;

import java.awt.geom.Point2D;
import java.util.Random;

public class SprayShooter extends Shooter {

	@Override
	public void shoot(GraphicsInfo ginfo, Stage stage, Point2D.Double position) {

        Random l = new Random();

		double r = 180.0 / 180.0 * Math.PI;  //弾の飛んでいく向き
		BulletChara bullet = (BulletChara) stage.searchBullet();
		if(bullet == null) return;
		bullet.mover = StraightMover.singleton;
		bullet.position.x =    l.nextInt(780);          //乱数を取得する //position.x;
		bullet.position.y = 570;
		bullet.vector.x = 0.0;
		bullet.vector.y = 0.0;
		bullet.vector.rotateVector(r);
		bullet.setImage(stage.getBulletImage(Stage1.INA));
		bullet.setVisible(ginfo, true);
	}

	public static SprayShooter singleton = new SprayShooter();

}
