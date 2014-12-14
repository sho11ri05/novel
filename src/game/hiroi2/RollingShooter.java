package game.hiroi2;

import java.awt.geom.Point2D;
import java.util.Random;

public class RollingShooter extends Shooter {

	@Override
	public void shoot(GraphicsInfo ginfo, Stage stage, Point2D.Double position) {

        Random l = new Random();

		double r = 180.0 / 180.0 * Math.PI;  //弾の飛んでいく向き
		BulletChara bullet = stage.searchBullet();
		if(bullet == null) return;
		bullet.mover = StraightMover.singleton;
		bullet.position.x =  l.nextInt(780);          //乱数を取得する //position.x;
		bullet.position.y =570;  // position.y;
		bullet.vector.x = 0;
		bullet.vector.y = 120;
		bullet.vector.rotateVector(r);
		bullet.setImage(stage.getBulletImage(Stage1.INA));
		bullet.setVisible(ginfo, true);
	}

	public static RollingShooter singleton = new RollingShooter();

}
