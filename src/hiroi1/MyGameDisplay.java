package hiroi1;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class MyGameDisplay extends GameDisplay {

	GameDisplay title, main;
	public GameDisplay over;
	GameDisplay clear;
	private Font mfont = new Font("Sanserif", Font.BOLD, 50);
	ArrayList<Stage> stagelist = new ArrayList<Stage>();
	Stage stage = null;
	int stagenum = 0;

	public MyGameDisplay(){
		this.title = new MyGameTitle();
		this.main = new MyGameMain();
		this.over = new MyGameOver();
		GameDisplay.current = this.title;
		stagelist.add(new Stage1());
		this.stage = stagelist.get(0);
	}


	@Override
	public void show(GraphicsInfo ginfo) {
	}

	@Override
	public void loadMedia() throws IOException {
		this.title.loadMedia();
		this.main.loadMedia();
		this.over.loadMedia();
		for(int i=0; i<this.stagelist.size(); i++){
			this.stagelist.get(i).loadMedia();
		}
	}


	//タイトル画面
	class MyGameTitle extends GameDisplay{
		private BufferedImage img_title;

		@Override
		public void show(GraphicsInfo ginfo) {
			ginfo.g.drawImage(this.img_title, 0, 0, null);

			ginfo.g.setColor(Color.RED);
			ginfo.g.setFont(MyGameDisplay.this.mfont);
			String str = "PUSH SPACE";
			FontMetrics fm = ginfo.g.getFontMetrics();
			int strw = fm.stringWidth(str) / 2;
			ginfo.g.drawString(str, 550 - strw, 500);

			if(ginfo.keystate[KEY_STATE.SPACE] == true){
				GameDisplay.current = MyGameDisplay.this.main;
				GameDisplay.current.setStartTime(ginfo.currenttime);
				MyGameDisplay.this.stagenum = 0;
				MyGameDisplay.this.stage = stagelist.get(MyGameDisplay.this.stagenum);
				MyGameDisplay.this.stage.init(ginfo);
			}
		}

		@Override
		public void loadMedia() throws IOException {
			this.img_title = ImageIO.read(new File("hiroi1_gazou/hiroi1.jpg"));
		}
	}


	//ゲーム本編
	class  MyGameMain extends GameDisplay{
		@Override

		public void show(GraphicsInfo ginfo) {
			MyGameDisplay.this.stage.draw(ginfo);
			if(ginfo.currenttime - this.starttime > 20000){
				//180000に戻す
				GameDisplay.current = MyGameDisplay.this.over;
				GameDisplay.current.setStartTime(ginfo.currenttime);
			}

			if(MyGameDisplay.this.stage.hitTestAll(ginfo) == true){
			}
		}

		@Override
		public void loadMedia() throws IOException {
		}
	}



	//ゲーム終了画面
	class Game extends Stage1{
	}

	class  MyGameOver extends GameDisplay{

		@Override
		public void show(GraphicsInfo ginfo) {
			MyGameDisplay.this.stage.draw(ginfo);
			ginfo.g.setColor(Color.RED);
			ginfo.g.setFont(MyGameDisplay.this.mfont);
			String str = "雪集め終了！";
			FontMetrics fm = ginfo.g.getFontMetrics();
			int strw = fm.stringWidth(str) / 2;
			ginfo.g.drawString(str, 400 - strw, 200);


//			ginfo.g.setColor(Color.BLUE);
//			ginfo.g.setFont(MyGameDisplay.this.mfont);
//			String str = "Total Score："+this.score;
//			FontMetrics fm = ginfo.g.getFontMetrics();
//			int strw = fm.stringWidth(str) / 2;
//			ginfo.g.drawString(str, 650 - strw, 100);

		}

		@Override
		public void loadMedia() {}
	}
}
