package game.hiroi1;

import java.awt.geom.Point2D;
import java.util.Random;

public class TargetShooter extends Shooter {

	private Vector2D v = new Vector2D();

	@Override
	public void shoot(GraphicsInfo ginfo, Stage stage, Point2D.Double position) {

        Random r = new Random();

		BulletChara bullet = stage.searchBullet();
		if(bullet == null) return;
		bullet.mover = StraightMover.singleton;
		bullet.position.x =    r.nextInt(780);          //乱数を取得する//position.x;
		bullet.position.y = 10;  //position.y;
//		bullet.vector.x = stage.getPlayer().position.x;
//		bullet.vector.y = stage.getPlayer().position.y;
		v.x = bullet.position.x;
		v.y = bullet.position.y;
		bullet.vector.subVector(v);
		bullet.vector.normalize();
		bullet.vector.x *= 500;
		bullet.vector.y *= 500;
//		double r = bullet.vector.angle();
//		bullet.angle = r / Math.PI * 180.0;
		bullet.setImage(stage.getBulletImage(Stage1.REDBULLET_E));
		bullet.setVisible(ginfo, true);
	}

	public static TargetShooter singleton = new TargetShooter();
}
