package game.hiroi2;


public class BulletChara extends GameChara {

	public BulletMover mover = null;
	protected long starttime;
	public Vector2D vector = new Vector2D();
	private int kind;


	public int getKind() {
		return kind;
	}

	public void setKind(int kind) {
		this.kind = kind;
	}

	public BulletChara(){
		this.visible = false;
		this.size = 10;
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
