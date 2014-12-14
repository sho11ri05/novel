package game.hiroi1;

import java.awt.geom.Point2D;
import java.util.Random;

public class RollingShooter extends Shooter {

	@Override
	public void shoot(GraphicsInfo ginfo, Stage stage, Point2D.Double position) {

        Random r = new Random();

//		double r = Effect.linear(ginfo, 5000, 0.0, Math.PI * 2);
		BulletChara bullet = stage.searchBullet();
		if(bullet == null) return;
		bullet.mover = StraightMover.singleton;
		bullet.position.x =  r.nextInt(780);          //乱数を取得する //position.x;
		bullet.position.y =10;  // position.y;
		bullet.vector.x = 0;
		bullet.vector.y = 120;
//		bullet.vector.rotateVector(-r);
//		bullet.setImage(stage.getBulletImage(Stage1.BLUEBULLET_E));
		bullet.setImage(stage.getBulletImage(Stage1.REDBULLET_E));
		bullet.setVisible(ginfo, true);
	}

	public static RollingShooter singleton = new RollingShooter();

}
