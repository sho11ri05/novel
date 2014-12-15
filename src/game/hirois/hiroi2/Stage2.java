package game.hirois.hiroi2;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Stage2 extends Stage {

	private PlayerChara player = new PlayerChara();
	private BufferedImage img_chara, img_back, img_shot;
	private BossB1 enemy = new BossB1();
	private BossB2 enemy2 = new BossB2();
	public static final int MAX_BULLETS_E = 200;
	private ArrayList<BulletChara> bullets_e =
			new ArrayList<BulletChara>(MAX_BULLETS_E);
	public static final int MAX_BULLETS_J = 20;
	private ArrayList<BulletChara> bullets_j =
			new ArrayList<BulletChara>(MAX_BULLETS_J);
	private ArrayList<BufferedImage> img_bullets =
			new ArrayList<BufferedImage>();
	public static final int REDBULLET_E = 0;
	public static final int GREENBULLET_E = 1;
	public static final int BLUEBULLET_E = 2;
	public static final int PURPLEBULLET_E = 3;
	public static final int BULLET_J = 4;

	@Override
	public GameChara getPlayer() {
		return this.player;
	}

	@Override
	public void loadMedia() throws IOException {
		this.img_chara = ImageIO.read(new File("media/chara.png"));
		this.player.setImage(
				this.img_chara.getSubimage(0,  0, 48, 48));
		this.enemy2.setImage(
				this.img_chara.getSubimage(192, 72, 144, 144));
		this.enemy.setImage(
				this.img_chara.getSubimage(192+144, 72, 144, 144));
		this.img_back = ImageIO.read(new File("media/back.jpg"));
		this.img_shot = ImageIO.read(new File("media/shot.png"));
		this.img_bullets.add(this.img_shot.getSubimage(0, 0, 24, 24));
		this.img_bullets.add(this.img_shot.getSubimage(48, 0, 24, 24));
		this.img_bullets.add(this.img_shot.getSubimage(24, 0, 24, 24));
		this.img_bullets.add(this.img_shot.getSubimage(72, 0, 24, 24));
		this.img_bullets.add(this.img_shot.getSubimage(0, 24, 24, 48));
		//サウンド
		try {
			SoundBox.singleton.loadSound(
					new File("media/bom34.wav"));
			SoundBox.singleton.loadSound(
					new File("media/fall01.wav"));
			SoundBox.singleton.loadSound(
					new File("media/fm005.wav"));
			SoundBox.singleton.loadSound(
					new File("media/smoke03.wav"));
			SoundBox.singleton.loadSound(
					new File("media/burst01.wav"));
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	@Override
	public GameChara getEnemy() {
		return this.enemy;
	}
	public GameChara getEnemy2() {
		return this.enemy2;
	}

	@Override
	public void draw(GraphicsInfo ginfo) {
		ginfo.g.drawImage(this.img_back, 0, 0, null);
		this.enemy.draw(ginfo, this);
		this.enemy2.draw(ginfo, this);
		this.player.draw(ginfo, this);
		for(int i=0; i<MAX_BULLETS_E; i++){
			if(this.bullets_e.get(i).visible == true){
				this.bullets_e.get(i).draw(ginfo, this);
			}
		}
		for(int i=0; i<MAX_BULLETS_J; i++){
			if(this.bullets_j.get(i).visible == true){
				this.bullets_j.get(i).draw(ginfo, this);
			}
		}
	}

	@Override
	public void init(GraphicsInfo ginfo) {
		this.player.position.x = 400;
		this.player.position.y = 520;
//		this.player.life = true;
		this.enemy.position.x = 300;
		this.enemy.position.y = -100;
		this.enemy.init();
		this.enemy2.position.x = 500;
		this.enemy2.position.y = -100;
		this.enemy2.init();
		//弾の初期化
		this.bullets_e.clear();
		for(int i=0; i<MAX_BULLETS_E; i++){
			this.bullets_e.add(new BulletChara());
		}
		this.bullets_j.clear();
		for(int i=0; i<MAX_BULLETS_J; i++){
			this.bullets_j.add(new BulletChara());
		}
	}

	public ArrayList<BulletChara> getBullets_E() {
		return this.bullets_e;
	}

	@Override
	public BufferedImage getBulletImage(int type) {
		return this.img_bullets.get(type);
	}

//	@Override
//	public ArrayList<BulletChara> getBullets_J() {
//		return this.bullets_j;
//	}

	@Override
	public boolean hitTestAll(GraphicsInfo ginfo) {
		for(int i=0; i<MAX_BULLETS_J; i++){
			if(this.bullets_j.get(i).visible == true){
				if(this.enemy.life > 0){
					if(this.bullets_j.get(i).hitTest(this.enemy) == true){
						this.enemy.life--;
						this.bullets_j.get(i).setVisible(ginfo, false);
						SoundBox.singleton.playOneSHot(4);
					}
				}
				if(this.enemy2.life > 0){
					if(this.bullets_j.get(i).hitTest(this.enemy2) == true){
						this.enemy2.life--;
						this.bullets_j.get(i).setVisible(ginfo, false);
						SoundBox.singleton.playOneSHot(4);
					}
				}
			}
		}
		if(this.enemy.life > 0){
			if(this.enemy.hitTest(this.player) == true){
//				this.player.life = false;
				return true;
			}
		}
		if(this.enemy2.life > 0){
			if(this.enemy2.hitTest(this.player) == true){
//				this.player.life = false;
				return true;
			}
		}
		for(int i=0; i<MAX_BULLETS_E; i++){
			if(this.bullets_e.get(i).visible == true){
				if(this.bullets_e.get(i).hitTest(this.player) == true){
//					this.player.life = false;
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public boolean isBossLiving() {
		if(this.enemy.life < 1 && this.enemy2.life < 1) return false;
		return true;
	}

	@Override
	public ArrayList<BulletChara> getIna() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

//	@Override
//	public ArrayList<BulletChara> getYuki1() {
//		// TODO 自動生成されたメソッド・スタブ
//		return null;
//	}
//
//	@Override
//	public ArrayList<BulletChara> getYuki2() {
//		// TODO 自動生成されたメソッド・スタブ
//		return null;
//	}
//
//	@Override
//	public ArrayList<BulletChara> getAme() {
//		// TODO 自動生成されたメソッド・スタブ
//		return null;
//	}

}
