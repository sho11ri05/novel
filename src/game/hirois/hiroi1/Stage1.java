package game.hirois.hiroi1;

import game.GraphicsInfo;
import game.SoundBox;
import game.hirois.BulletCharaAbstract;
import game.hirois.GameChara;
import game.hirois.Stage;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Stage1 extends Stage {
	private PlayerChara player = new PlayerChara();
	private BufferedImage img_chara, img_back, img_shot;
	private BossA enemy = new BossA();
	public static final int YUKI_1 = 2000; //雪の弾数

	private ArrayList<BulletCharaAbstract> yuki1 = new ArrayList<BulletCharaAbstract>(0);

	private ArrayList<BufferedImage> img_bullets =
			new ArrayList<BufferedImage>();
	public static int REDBULLET_E = 0 ;
	//finalを消した

	public int score =0;
	private Font mfont = new Font("Sanserif", Font.BOLD, 30);

	public Stage1() {
		ArrayList<BulletChara> array = new ArrayList<BulletChara>(YUKI_1);
		for (int i = 0; i < array.size(); i++) {
			yuki1.add((BulletCharaAbstract) array.get(i));
		}
	}
	
	@Override
	public GameChara getPlayer() {
		return this.player;
	}

	@Override
	public void loadMedia() throws IOException {
		this.img_chara = ImageIO.read(new File("gazou/zeusu.png"));
		this.player.setImage(
		this.img_chara.getSubimage(0,  0, 100, 100));
		this.img_back = ImageIO.read(new File("gazou/hiroi2-2.jpg"));
		this.img_shot = ImageIO.read(new File("hiroi1_gazou/yuki.png"));
		this.img_bullets.add(this.img_shot.getSubimage(0, 0, 34, 34));

		//サウンド
		try {
			//自機が当たった時の音
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

	@Override
	public void draw(GraphicsInfo ginfo) {
		ginfo.g.drawImage(this.img_back, 0, 0, null);
		this.enemy.draw(ginfo, this);
		this.player.draw(ginfo, this);
		for(int i=0; i<YUKI_1; i++){
			if(this.yuki1.get(i).visible == true){
				this.yuki1.get(i).draw(ginfo, this);
			}
		}
	}

	@Override
	public void init(GraphicsInfo ginfo) {
//		プレイヤー横配置
		this.player.position.x = 50;
//		プレイヤー縦配置
		this.player.position.y = 320;
		this.enemy.position.x = 400;
		this.enemy.position.y = -100;
		this.enemy.init();
		//弾の初期化
		this.yuki1.clear();
		for(int i=0; i<YUKI_1; i++){
			this.yuki1.add(new BulletChara());
		}
	}

	@Override
	public ArrayList<BulletCharaAbstract> getBullet() {
		return this.yuki1;
	}

	@Override
	public BufferedImage getBulletImage(int type) {
		return this.img_bullets.get(type);
	}


	@Override
	public boolean hitTestAll(GraphicsInfo ginfo) {
		if(this.getEnemy().hitTest(this.player) == true){
			return true;
		}



		//敵の弾の当たり値
		for(int i=0; i<2000; i++){
			if(this.yuki1.get(i).visible == true){
				if(this.yuki1.get(i).hitTest(this.player) == true){
					score = score + 10;
					this.yuki1.get(i).visible = false;
					return true;
				}
			}
		}


		//スコア
		ginfo.g.setColor(Color.BLUE);
		ginfo.g.setFont(Stage1.this.mfont);
		String str = "Total Score："+score;
		FontMetrics fm = ginfo.g.getFontMetrics();
		int strw = fm.stringWidth(str) / 2;
		ginfo.g.drawString(str, 650 - strw, 100);
		return false;

	}


	@Override
	public boolean isBossLiving() {
		if(this.enemy.life < 1) return false;
		return true;
	}


}
