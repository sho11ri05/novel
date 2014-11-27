package hiroi2;


public class StraightMover extends BulletMover {
	public void move(GraphicsInfo ginfo, Stage stage, BulletChara bullet) {
		if(bullet.position.x < 0 ||
			bullet.position.x > stage.getWidth() ||
			bullet.position.y < 0 ||
			bullet.position.y > stage.getHeight())
		{
			bullet.visible = false;
		}
		bullet.position.x += bullet.vector.x * ginfo.frametime * 2;
		bullet.position.y += bullet.vector.y * ginfo.frametime * 2;
	}

	public static StraightMover singleton = new StraightMover();
}
