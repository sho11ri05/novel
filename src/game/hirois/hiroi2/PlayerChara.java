package game.hirois.hiroi2;

import game.GraphicsInfo;
import game.KEY_STATE;
import game.hirois.GameChara;
import game.hirois.Stage;
import game.hirois.Vector2D;


public class PlayerChara extends GameChara {
	public static final double SPEED = 300;
	long lastshooting = 0;
	int searchidx = 0;
	int shootcount = 0;
	Vector2D shootvec = new Vector2D(0, -500);
//	public boolean life = true;

	public PlayerChara(){
		this.size = 80;
	}

	@Override
	public GameChara draw(GraphicsInfo ginfo, Stage stage) {
//		if(this.life == false) return super.draw(ginfo, stage);
		//キー入力
		if(ginfo.keystate[KEY_STATE.LEFT]){
			this.position.x -= PlayerChara.SPEED * ginfo.frametime;
		}
		if(ginfo.keystate[KEY_STATE.RIGHT]){
			this.position.x += PlayerChara.SPEED * ginfo.frametime;
		}
		if(ginfo.keystate[KEY_STATE.UP]){
			this.position.y -= PlayerChara.SPEED * ginfo.frametime;
		}
		if(ginfo.keystate[KEY_STATE.DOWN]){
			this.position.y += PlayerChara.SPEED * ginfo.frametime;
		}
		if(this.position.x < 0) {
			this.position.x = 0;
		}
		if(this.position.x > stage.getWidth()) {
			this.position.x = stage.getWidth();
		}
		if(this.position.y < 0) {
			this.position.y = 0;
		}
		if(this.position.y > stage.getHeight()){
			this.position.y = stage.getHeight();
		}

		return super.draw(ginfo, stage);
	}



}


