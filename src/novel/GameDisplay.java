package novel;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;



public abstract class GameDisplay {

	protected static GameDisplay current = null;
	protected long starttime;
	protected long endtime;  //終わり時間

	//開始時間設定
//	public void setStartTime(long st){
//		this.starttime = st;
//	}

	//現在のディスプレイを返す
	public GameDisplay getCurrentDisplay(){
		return GameDisplay.current;
	}
	
	//会話、ナレーションのテキスト部分を描画するメソッド
	protected void drowTextField(int row, GraphicsInfo ginfo, BufferedImage img_waku, String[] kotoba, int countPush) {
		  ginfo.g.drawImage(img_waku, 100, 350, null);
		  ginfo.g.setColor(Color.WHITE);
		  ginfo.g.setFont(new Font("Sanserif", Font.BOLD, 15));

		  row -= 1;
		  for (int i = 0; row >= 0; row--) {
		    String str = kotoba[countPush - row];
		    ginfo.g.drawString(str, 380 - ginfo.g.getFontMetrics().stringWidth(str) / 2, 400 + (20 * i));
		    i++;
		  }
	}

	//このディスプレイを表示
	public abstract void show(GraphicsInfo ginfo);

	//画像などを読み込む
	public abstract void loadMedia() throws IOException;

}
