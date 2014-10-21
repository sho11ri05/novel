package novel;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MyGameDisplay extends GameDisplay {

	GameDisplay title, main, end;
	private Font mfont = new Font("Sanserif", Font.BOLD, 35);
	private Font mfont2 = new Font("Sanserif", Font.BOLD, 15);

	public MyGameDisplay(){
		this.title = new MyGameTitle();
		this.main = new MyGameMain();
		this.end = new MyGameEnd();
		GameDisplay.current = this.title;
	}

	@Override
	public void show(GraphicsInfo ginfo) {
	}

	@Override
	public void loadMedia() throws IOException {
		this.title.loadMedia();
		this.main.loadMedia();
		this.end.loadMedia();
	}


	//内容


	//タイトル
	class MyGameTitle extends GameDisplay{
		private BufferedImage img_title;

		@Override
		public void show(GraphicsInfo ginfo) {
			ginfo.g.drawImage(this.img_title, 0, 0, null);

			ginfo.g.setColor(Color.RED);
			ginfo.g.setFont(MyGameDisplay.this.mfont);
			String str = "Push State!";
			FontMetrics fm = ginfo.g.getFontMetrics();
			int strw = fm.stringWidth(str) / 2;
			ginfo.g.drawString(str, 200 - strw, 330);

			if(ginfo.keystate[KEY_STATE.SPACE] == true){
				GameDisplay.current = MyGameDisplay.this.main;
			}
		}

		@Override
		public void loadMedia() throws IOException {
			this.img_title = ImageIO.read(new File("gazou/title.jpg"));
		}
	}



	//本編
	class  MyGameMain extends GameDisplay{
//		ArrayList<String> array = new ArrayList<String>();
		private BufferedImage img_back1;
		private BufferedImage img_waku;
		private BufferedImage img_apo;

		@Override
		public void show(GraphicsInfo ginfo) {
			String str;
			String[] kotoba = {
					"サンプル１", "サンプル２", "サンプル３"
			};
			ginfo.g.drawImage(this.img_back1, 0, 0, null);
			ginfo.g.drawImage(this.img_apo, 500, 80, null);
			ginfo.g.drawImage(this.img_waku, 100, 350, null);

			ginfo.g.setColor(Color.WHITE);
			ginfo.g.setFont(MyGameDisplay.this.mfont2);
//			String str = "サンプル";
			str = kotoba[0];
			FontMetrics fm = ginfo.g.getFontMetrics();
			int strw = fm.stringWidth(str) / 2;
			ginfo.g.drawString(str, 200 - strw, 400);

			if(ginfo.keystate[KEY_STATE.SPACE] == true){
//				for(int i =0; i>6; i++){
					ginfo.g.setColor(Color.WHITE);
					ginfo.g.setFont(MyGameDisplay.this.mfont2);
					str =kotoba[1];
					fm = ginfo.g.getFontMetrics();
					strw = fm.stringWidth(str) / 2;
					ginfo.g.drawString(str, 200 - strw, 400);
//				}
			}
		}

		@Override
		public void loadMedia() throws IOException {
			this.img_back1 = ImageIO.read(new File("gazou/rouka00.jpg"));
			this.img_apo = ImageIO.read(new File("apolon/aporon_a.png"));
			this.img_waku = ImageIO.read(new File("gazou/ant1.png"));
		}
	}



	//エンド
	class MyGameEnd extends GameDisplay{

		@Override
		public void show(GraphicsInfo ginfo) {
			// TODO 自動生成されたメソッド・スタブ

		}

		@Override
		public void loadMedia() throws IOException {
			// TODO 自動生成されたメソッド・スタブ

		}
	}


}