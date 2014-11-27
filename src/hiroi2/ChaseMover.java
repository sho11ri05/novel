package hiroi2;


public class ChaseMover extends BulletMover {

	private Vector2D v = new Vector2D();

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
		if(l % 1500 < 10){
			bullet.vector.x = stage.getPlayer().position.x;
			bullet.vector.y = stage.getPlayer().position.y;
			v.x = bullet.position.x;
			v.y = bullet.position.y;
			bullet.vector.subVector(v);
			bullet.vector.normalize();
			bullet.vector.x *= 300;
			bullet.vector.y *= 300;
			double r = bullet.vector.angle();
			bullet.angle = r / Math.PI * 180.0;
		}
		bullet.position.x += bullet.vector.x * ginfo.frametime;
		bullet.position.y += bullet.vector.y * ginfo.frametime;
	}

	public static ChaseMover singleton = new ChaseMover();

}
