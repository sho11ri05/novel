package game.hirois;

import game.hirois.hiroi1.BulletChara;
import game.hirois.hiroi1.Stage;


public class TornadeMover extends BulletMover {

	@Override
	public void move(GraphicsInfo ginfo, Stage stage, BulletChara bullet) {
		if( bullet.position.x < -200 ||
				bullet.position.x > stage.getWidth() + 200 ||
				bullet.position.y < -200 ||
				bullet.position.y > stage.getHeight() + 200)
		{
			bullet.visible = false;
		}
		long l = ginfo.currenttime - bullet.getStartTime();
		bullet.vector.x = 0;
		bullet.vector.y = l / 50;
		bullet.vector.rotateVector(-l*0.001);
		bullet.position.x = stage.getEnemy().position.x + bullet.vector.x;
		bullet.position.y = stage.getEnemy().position.y + bullet.vector.y;
	}

	public static TornadeMover singleton = new TornadeMover();

}
