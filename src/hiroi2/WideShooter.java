package hiroi2;

import java.awt.geom.Point2D;
import java.util.Random;

public class WideShooter extends Shooter {

	private Vector2D v = new Vector2D();

	@Override
	public void shoot(GraphicsInfo ginfo, Stage stage, Point2D.Double position) {

        Random r = new Random();

		this.v.x = 0.0;
		this.v.y = 240.0;
		//初期値

//		double r = 180.0 * Math.PI;
		//-30度回転させていた(らしい)　元はdouble r = -30.0 / 180.0 * Math.PI;
		//ラジアンで度を求める　やり方はネットにて
//		this.v.rotateVector(r);
//		r = 20.0 / 180.0 * Math.PI;
//		for(int i=0; i<4; i++){
			BulletChara bullet = stage.searchBullet();
			if(bullet == null) return;
			bullet.mover = StraightMover.singleton;
			bullet.position.x =    r.nextInt(780);          //乱数を取得する //position.x;
			bullet.position.y = 10; //position.y;
			bullet.vector.x = this.v.x;
			bullet.vector.y = this.v.y;
			bullet.setImage(stage.getBulletImage(Stage1.INA));
			bullet.setVisible(ginfo, true);
//			this.v.rotateVector(r);
//		}

	}

	public static WideShooter singleton = new WideShooter();

}
