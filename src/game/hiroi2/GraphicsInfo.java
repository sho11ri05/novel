package game.hiroi2;

import java.awt.Graphics2D;

public class GraphicsInfo {
	//グラフィックス
	public Graphics2D g;
	//前のフレームからの秒数（速度調整用）
	public double frametime;
	//現在のミリ秒数
	public long currenttime;
	//キーステート
	public boolean[] keystate;

	//コンストラクタ
	public GraphicsInfo(){
		this.keystate = new boolean[8];
		for(int i=0; i<8; i++){
			this.keystate[i] = false;
		}
	}

}
