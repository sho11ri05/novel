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
	private int countPush = 0;



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
		private BufferedImage img_back2;
		private BufferedImage img_waku;

		private BufferedImage img_apo_a;
		private BufferedImage img_apo_k;
		private BufferedImage img_apo_s;

		private BufferedImage img_al_a;
		private BufferedImage img_al_s;

		private BufferedImage img_zeu_a;
		private BufferedImage img_zeu_d;
		private BufferedImage img_zeu_k;
		private BufferedImage img_zeu_s;

		@Override
		public void show(GraphicsInfo ginfo) {
			String kaigyo = System.getProperty("line.separator");
			String str;
			String[] kotoba = {
					"", "このゲームを始めたあなたは、"+ kaigyo +"『ゼウス』という神様を知っているだろうか…"
							+ "全知全能と言われ、雷を操ることができると言われるオリュンポスの神である。"
							+ "そのゼウスには実の姉であり、そして正妻であるヘラという女神がいる。"
							+ "この物語は、そんな二人の夫婦喧嘩から始まるのである…"
							, "サンプル2", "サンプル３"
			};
			ginfo.g.drawImage(this.img_back1, 0, 0, null);
			ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
			ginfo.g.drawImage(this.img_apo_s, 500, 80, null);
			ginfo.g.drawImage(this.img_al_s, 350, 95, null);
			ginfo.g.drawImage(this.img_waku, 100, 350, null);

			ginfo.g.setColor(Color.WHITE);
			ginfo.g.setFont(MyGameDisplay.this.mfont2);
			str = kotoba[countPush];
			FontMetrics fm = ginfo.g.getFontMetrics();
			int strw = 0;//fm.stringWidth(str) / 2;
			ginfo.g.drawString(str, 200 - strw, 400);

			if(ginfo.keystate[KEY_STATE.SPACE] == true){
				countPush = countPush + 1;
				try{
					Thread.sleep(300); //0.5秒Sleepする
					}catch(InterruptedException e){}
			}

			if(countPush == 1){
				ginfo.g.drawImage(this.img_back2, 0, 0, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);
				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = 0;//fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 200 - strw, 400);
			}else{
				return;
			}

			if(countPush == 2){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_al_a, 350, 95, null);
				ginfo.g.drawImage(this.img_apo_k, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);

				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);
				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 200 - strw, 400);
			}else{
				return;
			}


		}


		@Override
		public void loadMedia() throws IOException {
			this.img_back1 = ImageIO.read(new File("gazou/rouka00.jpg"));
			this.img_back2 = ImageIO.read(new File("gazou/sky.jpg"));
			this.img_waku = ImageIO.read(new File("gazou/ant1.png"));

			this.img_apo_a = ImageIO.read(new File("apolon/aporon_a.png"));//アポロン画像
			this.img_apo_k = ImageIO.read(new File("apolon/aporon_k.png"));
			this.img_apo_s = ImageIO.read(new File("apolon/aporon_s.png"));

			this.img_al_a = ImageIO.read(new File("altemis/arutemisu_a.png"));//アルテミス画像
			this.img_al_s = ImageIO.read(new File("altemis/arutemisu_s.png"));

			this.img_zeu_a = ImageIO.read(new File("zeus/zeusu_a.png"));//ゼウス画像
			this.img_zeu_d = ImageIO.read(new File("zeus/zeusu_d.png"));
			this.img_zeu_k = ImageIO.read(new File("zeus/zeusu_k.png"));
			this.img_zeu_s = ImageIO.read(new File("zeus/zeusu_s.png"));
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