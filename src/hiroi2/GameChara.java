package hiroi2;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class GameChara {

	protected BufferedImage img = null;
	public Point2D.Double position = new Point2D.Double(500,100);
	public Point2D.Double center = new Point2D.Double();
	public double angle = 0.0;
	public boolean visible = true;
	public double size;

	//画像を設定
	public GameChara setImage(BufferedImage img){
		this.img = img;
		this.center.x = this.img.getWidth()/2;
		this.center.y = this.img.getHeight()/2;
		return this;
	}

	public BufferedImage getImage(){
		return this.img;
	}

	public GameChara draw(GraphicsInfo ginfo, Stage stage){
		if(this.img == null) return this;
		if(this.visible == false) return this;
		//変型待避
		AffineTransform oldtr = ginfo.g.getTransform();
		ginfo.g.translate(this.position.x, this.position.y);
		ginfo.g.rotate(this.angle / 180.0 * Math.PI, 0, 0);
		ginfo.g.drawImage(this.img,
				-(int)this.center.x, -(int)this.center.y, null);
		//変型復帰
		ginfo.g.setTransform(oldtr);
		return this;
	}

	private Vector2D v = new Vector2D();

	public boolean hitTest(GameChara gc){
		this.v.x = this.position.x - gc.position.x;
		this.v.y = this.position.y - gc.position.y;
		double l = this.v.lengthSquare();
		double l2 = this.size * this.size + gc.size * gc.size;
		if(l < l2) return true;
		return false;
	}
}
