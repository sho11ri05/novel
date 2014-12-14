package game.hiroi1;

import java.awt.geom.Point2D;
import java.util.Random;

public class CrossShooter extends Shooter {

	private Vector2D v = new Vector2D();

	@Override
	public void shoot(GraphicsInfo ginfo, Stage stage, Point2D.Double position) {

        Random r = new Random();

//		double r = 90.0 / 180.0 * Math.PI;
		this.v.x = 0.0;
		this.v.y = 200.0;
		for(int i=0; i<1; i++){
			BulletChara bullet = stage.searchBullet();
			if(bullet == null) return;
			bullet.mover = StraightMover.singleton;
			bullet.position.x = r.nextInt(780);          //乱数を取得する //position.x;
			bullet.position.y = 10;   //position.y;
			bullet.vector.x = this.v.x;
			bullet.vector.y = this.v.y;
//			bullet.setImage(stage.getBulletImage(Stage1.GREENBULLET_E));
			bullet.setImage(stage.getBulletImage(Stage1.REDBULLET_E));
			bullet.setVisible(ginfo, true);
//			this.v.rotateVector(r);
		}

		for(int i=0; i<1; i++){
			BulletChara bullet = stage.searchBullet();
			if(bullet == null) return;
			bullet.mover = StraightMover.singleton;
			bullet.position.x = r.nextInt(780);          //乱数を取得する //position.x;
			bullet.position.y = 10;   //position.y;
			bullet.vector.x = this.v.x;
			bullet.vector.y = this.v.y;
//			bullet.setImage(stage.getBulletImage(Stage1.GREENBULLET_E));
			bullet.setImage(stage.getBulletImage(Stage1.REDBULLET_E));
			bullet.setVisible(ginfo, true);
//			this.v.rotateVector(r);
		}
	}

	public static CrossShooter singleton = new CrossShooter();

}
