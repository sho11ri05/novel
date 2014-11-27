package hiroi2;

import java.awt.geom.Point2D.Double;

@SuppressWarnings("serial")
public class Vector2D extends Double {
	//コンストラクタ
	public Vector2D(){
		this(0, 0);
	}
	public Vector2D(double x, double y){
		this.x = x;
		this.y = y;
	}

	//回転
	public Vector2D rotateVector(double radian){
		double ox = -Math.sin(radian) * this.y  + Math.cos(radian) * this.x;
		double oy =  Math.cos(radian) * this.y  + Math.sin(radian) * this.x;
		this.x = ox;
		this.y = oy;
		return this;
	}

	//引く
	public Vector2D subVector(Vector2D in){
		this.x -= in.x;
		this.y -= in.y;
		return this;
	}

	//足す
	public Vector2D addVector(Vector2D in){
		this.x += in.x;
		this.y += in.y;
		return this;
	}

	//長さを調べる
	public double lengthSquare(){
		return this.x * this.x + this.y * this.y;
	}

	//正規化（長さ1のベクトルを求める）
	public Vector2D normalize(){
		double l = Math.sqrt(this.lengthSquare());
		this.x /= l;
		this.y /= l;
		return this;
	}

	//角度を求める
	public double angle(){
		return Math.atan2(this.y, this.x) - (Math.PI / 2.0);
	}

	//内積・外積
	public double dotProduct(Vector2D in){
		return this.x * in.x + this.y * in.y;
	}
	public double crossProduct(Vector2D in){
		return this.x * in.y - this.y * in.x;
	}

}
