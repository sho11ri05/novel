package game.hirois.hiroi1;

import game.GraphicsInfo;
import game.hirois.BulletCharaAbstract;
import game.hirois.Shooter;
import game.hirois.Stage;

import java.awt.geom.Point2D;
import java.util.Random;

public class SplatterShooter extends Shooter {

	@Override
	public void shoot(GraphicsInfo ginfo, Stage stage, Point2D.Double position) {

        Random r = new Random();

//		double r = (Math.random() * 60 - 30.0)  / 180.0 * Math.PI;
		BulletChara bullet = (BulletChara) stage.searchBullet();
		if(bullet == null) return;
		bullet.mover = StraightMover.singleton;
		bullet.position.x =   r.nextInt(780);          //乱数を取得する //position.x;
		bullet.position.y =10;  //position.y;
		bullet.vector.x = 0.0;
		bullet.vector.y = 120.0;
//		bullet.vector.rotateVector(r);
//		bullet.setImage(stage.getBulletImage(Stage1.BLUEBULLET_E));
		bullet.setImage(stage.getBulletImage(Stage1.REDBULLET_E));
		bullet.setVisible(ginfo, true);

	}

	public static SplatterShooter singleton = new SplatterShooter();

}
