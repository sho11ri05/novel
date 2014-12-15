package game.hirois.hiroi1;

import java.awt.Color;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class BossB2 extends GameChara {
	private ArrayList<Pattern> patternlist = new ArrayList<Pattern>();
	private Pattern curpat;
	public int life = 100;

	public BossB2(){
		this.patternlist.add(new PatternB1());
		this.curpat = this.patternlist.get(0);
		this.patternlist_addlast(new PatternHalt(1000));
		this.patternlist_addlast(new PatternA3());
		this.patternlist_addlast(new PatternA4());
		this.patternlist_addlast(new PatternA5());
		this.patternlist_addlast(new PatternA4());
		this.patternlist_addlast(new PatternB4());
		this.patternlist_addlast(new PatternB5());
		int l = this.patternlist.size();
		this.patternlist.get(l-1).setNext(this.patternlist.get(6));


		this.size = 70;
	}

	//最後の要素と連結して追加
	void patternlist_addlast(Pattern pt){
		int s = this.patternlist.size();
		this.patternlist.get(s-1).setNext(pt);
		this.patternlist.add(pt);
	}


	public void init(){
		this.curpat = this.patternlist.get(0);
		this.life = 100;
	}

	Line2D.Double lifemeter = new Line2D.Double(500, 60, 10, 60);
	@Override
	public GameChara draw(GraphicsInfo ginfo, Stage stage) {
		if(this.life < 1) return this;

		if(this.curpat == null) return super.draw(ginfo, stage);

		if(this.curpat.isFinished(ginfo) == false){
			this.curpat.move(ginfo, stage);
		} else {
			this.curpat = curpat.getNext();
			if(this.curpat != null) this.curpat.start(ginfo);
		}

		//ライフメーター
		ginfo.g.setColor(Color.BLUE);
		this.lifemeter.x2 = 500 + this.life;
		ginfo.g.draw(this.lifemeter);

		return super.draw(ginfo, stage);
	}

	//出現
	class PatternB1 extends Pattern{
		@Override
		public boolean isFinished(GraphicsInfo ginfo) {
			if(BossB2.this.position.y > 200) return true;
			return false;
		}
		@Override
		public void move(GraphicsInfo ginfo, Stage stage) {
			BossB2.this.position.y += 120 * ginfo.frametime;
		}
	}
	//停止
	class PatternHalt extends Pattern{
		PatternHalt(long l){
			this.limit = l;
		}
		@Override
		public boolean isFinished(GraphicsInfo ginfo) {
			if(ginfo.currenttime - this.starttime > this.limit) {
				return true;
			}
			return false;
		}
		@Override
		public void move(GraphicsInfo ginfo, Stage stage) {
		}
	}

	//左移動
	class PatternA2 extends Pattern{
		@Override
		public boolean isFinished(GraphicsInfo ginfo) {
			if(BossB2.this.position.x < 100) return true;
			return false;
		}
		@Override
		public void move(GraphicsInfo ginfo, Stage stage) {
			BossB2.this.position.x -= 240 * ginfo.frametime;
		}
	}
	//右移動
	class PatternA3 extends Pattern{
		@Override
		public boolean isFinished(GraphicsInfo ginfo) {
			if(BossB2.this.position.x > 800-100) return true;
			return false;
		}
		@Override
		public void move(GraphicsInfo ginfo, Stage stage) {
			BossB2.this.position.x += 240 * ginfo.frametime;
		}
	}
	//下移動
	class PatternA4 extends Pattern{
		@Override
		public boolean isFinished(GraphicsInfo ginfo) {
			if(BossB2.this.position.y > 720-100) return true;
			return false;
		}
		@Override
		public void move(GraphicsInfo ginfo, Stage stage) {
			BossB2.this.position.y += 240 * ginfo.frametime;
		}
	}
	//上移動
	class PatternA5 extends Pattern{
		@Override
		public boolean isFinished(GraphicsInfo ginfo) {
			if(BossB2.this.position.y < 100) return true;
			return false;
		}
		@Override
		public void move(GraphicsInfo ginfo, Stage stage) {
			BossB2.this.position.y -= 240 * ginfo.frametime;
		}
	}
	//下移動
	class PatternB4 extends Pattern{
		private long lastshooting = 0;

		@Override
		public boolean isFinished(GraphicsInfo ginfo) {
			if(BossB2.this.position.y > 720-100) return true;
			return false;
		}
		@Override
		public void move(GraphicsInfo ginfo, Stage stage) {
			BossB2.this.position.y += 240 * ginfo.frametime;
			if(ginfo.currenttime - this.lastshooting > 1000){
				WideShooterR.singleton.shoot(ginfo, stage, BossB2.this.position);
				this.lastshooting = ginfo.currenttime;
			}
		}
	}
	//上移動
	class PatternB5 extends Pattern{
		private long lastshooting = 0;

		@Override
		public boolean isFinished(GraphicsInfo ginfo) {
			if(BossB2.this.position.y < 100) return true;
			return false;
		}
		@Override
		public void move(GraphicsInfo ginfo, Stage stage) {
			BossB2.this.position.y -= 240 * ginfo.frametime;
			if(ginfo.currenttime - this.lastshooting > 1000){
				WideShooterR.singleton.shoot(ginfo, stage, BossB2.this.position);
				this.lastshooting = ginfo.currenttime;
			}
		}
	}


}
