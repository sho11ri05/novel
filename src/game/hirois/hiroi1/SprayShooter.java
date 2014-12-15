package game.hirois.hiroi1;

import game.hirois.GraphicsInfo;
import game.hirois.Shooter;

import java.awt.geom.Point2D;
import java.util.Random;

public class SprayShooter extends Shooter {

	@Override
	public void shoot(GraphicsInfo ginfo, Stage stage, Point2D.Double position) {

        Random r = new Random();

//		double d = Effect.linear(ginfo, 3000, 0.0, Math.PI * 2.0);
//		double r = Math.sin(d) / 2;
		BulletChara bullet = stage.searchBullet();
		if(bullet == null) return;
		bullet.mover = StraightMover.singleton;
		bullet.position.x =    r.nextInt(780);          //乱数を取得する //position.x;
		bullet.position.y = 10;
		bullet.vector.x = 0.0;
		bullet.vector.y = 0.0;
//		bullet.vector.rotateVector(r);
//		bullet.setImage(stage.getBulletImage(Stage1.BLUEBULLET_E));
		bullet.setImage(stage.getBulletImage(Stage1.REDBULLET_E));
		bullet.setVisible(ginfo, true);
	}

	public static SprayShooter singleton = new SprayShooter();

}
