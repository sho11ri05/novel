package game.hiroi1;

import java.util.ArrayList;

public class BossA extends GameChara {

	private ArrayList<Pattern> patternlist = new ArrayList<Pattern>();
	private Pattern curpat;
	public int life = 100;

	//コンストラクタ
	public BossA(){
		this.patternlist.add(new PatternA1());
		this.curpat = this.patternlist.get(0);
		this.patternlist_addlast(new PatternBasicShoot(5000));
		this.patternlist_addlast(new PatternShootA2(3000));
		this.patternlist_addlast(new PatternShootA3(5000));
		this.patternlist_addlast(new PatternA2());
		this.patternlist_addlast(new PatternHalt(5000));
		this.patternlist_addlast(new PatternShootA3(5000));
		this.patternlist_addlast(new PatternS());
		this.patternlist_addlast(new PatternShootA4(5000));
		this.patternlist_addlast(new PatternA3());
		this.patternlist_addlast(new PatternShootA3(5000));
		this.patternlist_addlast(new PatternS());
		this.patternlist_addlast(new PatternShootA4(5000));
		this.patternlist_addlast(new PatternA4());
		int l = this.patternlist.size();
		this.patternlist.get(l-1).setNext(this.patternlist.get(1));

//		this.size = 90;　弾のサイズ
	}
	//最後の要素と連結して追加
	void patternlist_addlast(Pattern pt){
		int s = this.patternlist.size();
		this.patternlist.get(s-1).setNext(pt);
		this.patternlist.add(pt);
	}

	public void init(){
		this.curpat = this.patternlist.get(0);
		this.life = 200;
	}

//	Ellipse2D.Double auraelli = new Ellipse2D.Double(0,0, 240, 240);
//	Line2D.Double lifemeter = new Line2D.Double(10, 60, 10, 60);
	@Override
	public GameChara draw(GraphicsInfo ginfo, Stage stage) {
//		if(this.life < 1) return super.draw(ginfo, stage);
//		float alpha = (float)Effect.linear(ginfo, 3000, 0.8, 0.0);
//		ginfo.g.setColor(new Color(1.0f, 1.0f, 0.0f, alpha));
//		double size = Effect.linear(ginfo, 3000, 80, 320);
//		this.auraelli.x = this.position.x - size / 2;
//		this.auraelli.y = this.position.y - size / 2;
//		this.auraelli.width = size;
//		this.auraelli.height = size;
//		ginfo.g.fill(this.auraelli);

		if(this.curpat == null) return super.draw(ginfo, stage);

		if(this.curpat.isFinished(ginfo) == false){
			this.curpat.move(ginfo, stage);
		} else {
			this.curpat = curpat.getNext();
			if(this.curpat != null) this.curpat.start(ginfo);
		}

		//ライフメーター
//		ginfo.g.setColor(Color.RED);
//		this.lifemeter.x2 = 10 + this.life;
//		ginfo.g.draw(this.lifemeter);

		return super.draw(ginfo, stage);
	}

	//出現
	class PatternA1 extends Pattern{
		@Override
		public boolean isFinished(GraphicsInfo ginfo) {
			if(BossA.this.position.y > 200) return true;
			return false;
		}
		@Override
		public void move(GraphicsInfo ginfo, Stage stage) {
			BossA.this.position.y += 120 * ginfo.frametime;
		}
	}

	//左移動
	class PatternA2 extends Pattern{
		@Override
		public boolean isFinished(GraphicsInfo ginfo) {
			if(BossA.this.position.x < 100) return true;
			return false;
		}
		@Override
		public void move(GraphicsInfo ginfo, Stage stage) {
			BossA.this.position.x -= 240 * ginfo.frametime;
		}
	}
	//右移動
	class PatternA3 extends Pattern{
		@Override
		public boolean isFinished(GraphicsInfo ginfo) {
			if(BossA.this.position.x > 800-100) return true;
			return false;
		}
		@Override
		public void move(GraphicsInfo ginfo, Stage stage) {
			BossA.this.position.x += 240 * ginfo.frametime;
		}
	}
	//中央移動
	class PatternA4 extends Pattern{
		double spd;

		@Override
		public void start(GraphicsInfo ginfo) {
			if(BossA.this.position.x < 400) spd = 240;
			else spd = -240;
			super.start(ginfo);
		}
		@Override
		public boolean isFinished(GraphicsInfo ginfo) {
			if(Math.abs(BossA.this.position.x - 400) < 10) return true;
			return false;
		}
		@Override
		public void move(GraphicsInfo ginfo, Stage stage) {
			BossA.this.position.x += spd * ginfo.frametime;
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
	//突進
	class PatternS extends Pattern{
		boolean goforward;

		@Override
		public void start(GraphicsInfo ginfo) {
			this.goforward = true;
			super.start(ginfo);
		}
		@Override
		public boolean isFinished(GraphicsInfo ginfo) {
			if(this.goforward == false && BossA.this.position.y < 200){
				return true;
			}
			return false;
		}
		@Override
		public void move(GraphicsInfo ginfo, Stage stage) {
			if(this.goforward == true){
				BossA.this.position.y += 600 * ginfo.frametime;
				if(BossA.this.position.y > 500) this.goforward = false;
			} else {
				BossA.this.position.y -= 200 * ginfo.frametime;
			}
		}
	}
	//乱射
	class PatternBasicShoot extends Pattern{
		private long lastshooting = 0;

		PatternBasicShoot(long l){
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
			if(ginfo.currenttime - this.lastshooting > 2000){
				//TwinShooter.singleton.shoot(ginfo, stage, BossA.this.position);
				//CrossShooter.singleton.shoot(ginfo, stage, BossA.this.position);
				//WideShooter.singleton.shoot(ginfo, stage, BossA.this.position);
				//SprayShooter.singleton.shoot(ginfo, stage, BossA.this.position);
				SplatterShooter.singleton.shoot(ginfo, stage, BossA.this.position);
				//TargetShooter.singleton.shoot(ginfo, stage, BossA.this.position);
				//RollingShooter.singleton.shoot(ginfo, stage, BossA.this.position);
				//BasicShooter.singleton.shoot(ginfo, stage, BossA.this.position);
				this.lastshooting = ginfo.currenttime;
			}
		}
	}
	//発射2
	class PatternShootA2 extends PatternHalt{
		private long lastshooting = 0;

		PatternShootA2(long l) {
			super(l);
		}
		@Override
		public void move(GraphicsInfo ginfo, Stage stage) {
			if(ginfo.currenttime - this.lastshooting > 2000){
				RollingShooter.singleton.shoot(ginfo, stage, BossA.this.position);
				this.lastshooting = ginfo.currenttime;
			}
//			if(ginfo.currenttime - this.lastshooting2 > 500){
//				TargetShooter.singleton.shoot(ginfo, stage, BossA.this.position);
//				this.lastshooting2 = ginfo.currenttime;
//			}
		}
	}
	//発射3
	class PatternShootA3 extends PatternHalt{
		private long lastshooting = 0;

		PatternShootA3(long l) {
			super(l);
		}
		@Override
		public void move(GraphicsInfo ginfo, Stage stage) {
			if(ginfo.currenttime - this.lastshooting > 2000){
				CrossShooter.singleton.shoot(ginfo, stage, BossA.this.position);
				WideShooter.singleton.shoot(ginfo, stage, BossA.this.position);
				this.lastshooting = ginfo.currenttime;
			}
		}
	}
	//発射4
	class PatternShootA4 extends PatternHalt{
		private long lastshooting = 0;

		PatternShootA4(long l) {
			super(l);
		}
		@Override
		public void move(GraphicsInfo ginfo, Stage stage) {
			if(ginfo.currenttime - this.lastshooting > 1000){
				BasicShooter.singleton.shoot(ginfo, stage, BossA.this.position);
				this.lastshooting = ginfo.currenttime;
			}
		}
	}
}
