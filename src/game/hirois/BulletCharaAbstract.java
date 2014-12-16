package game.hirois;

import game.GraphicsInfo;
import game.hirois.BulletMover;
import game.hirois.GameChara;
import game.hirois.Stage;
import game.hirois.Vector2D;


public abstract class BulletCharaAbstract extends GameChara {

	public BulletMover mover = null;
	protected long starttime;
	public Vector2D vector = new Vector2D();
	private int kind;


	public int getKind() {
		return kind;
	}

	public void setKind(int k) {
		this.kind = k;
	}

	public BulletCharaAbstract(){
		this.visible = false;
		this.size = 12;
	}

	@Override
	public GameChara draw(GraphicsInfo ginfo, Stage stage) {
		if(this.mover != null){
			this.mover.move(ginfo, stage, this);
		}
		return super.draw(ginfo, stage);
	}

	public void setVisible(GraphicsInfo ginfo, boolean b){
		this.visible = b;
		if(b == true){
			starttime = ginfo.currenttime;
		}
	}
	public long getStartTime(){
		return this.starttime;
	}
}
