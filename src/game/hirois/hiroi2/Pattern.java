package game.hirois.hiroi2;


public abstract class Pattern {
	//次のパターン
	protected Pattern next = null;
	public void setNext(Pattern pt){
		this.next = pt;
	}
	public Pattern getNext(){
		return this.next;
	}

	//時間制限
	protected long limit, starttime;
	public void setLimit(long l){
		this.limit = l;
	}

	//開始
	public void start(GraphicsInfo ginfo){
		this.starttime = ginfo.currenttime;
	}

	//終了確認
	public abstract boolean isFinished(GraphicsInfo ginfo);

	//移動
	public abstract void move(GraphicsInfo ginfo, Stage stage);
}
