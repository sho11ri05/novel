package game.hiroi2;


public class AccelMover extends BulletMover {

	@Override
	public void move(GraphicsInfo ginfo, Stage stage, BulletChara bullet) {
		if( bullet.position.x < 0 ||
			bullet.position.x > stage.getWidth() ||
			bullet.position.y < 0 ||
			bullet.position.y > stage.getHeight())
		{
			bullet.visible = false;
		}
		long l = ginfo.currenttime - bullet.getStartTime();
		bullet.vector.normalize();
		double s = 100.0;
		if(l > 2000) s = s + (double)l / 5.0;
		bullet.vector.x *= s;
		bullet.vector.y *= s;
		bullet.position.x += bullet.vector.x * ginfo.frametime;
		bullet.position.y += bullet.vector.y * ginfo.frametime;
	}

	public static AccelMover singleton = new AccelMover();
}
