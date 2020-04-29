package objectRef;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Object {

	private int hp;
	private  int a,b;
	private double px,py;
	private  double v;
	private Color c;
	private Type type;
	Game game;
	
	public Object(int a,int b,double px,double py,int hp,Game game)
	{
		this.a=a;
		this.b=b;
		this.px=px;
		this.py=py;
		this.hp=hp;
		this.game=game;
	}
	
	public Object(int a,int b,double px,double py,int hp,Type type,Game game)
	{
		this.a=a;
		this.b=b;
		this.px=px;
		this.py=py;
		this.hp=hp;
		this.game=game;
		this.type=type;
	}
	
	public Object(int a,int b,double px,double py,double v,Game game)
	{
		this.a=a;
		this.b=b;
		this.px=px;
		this.py=py;
		this.v=v;
		this.game=game;
	}
	

	
	public abstract void update();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getA() {
		return a;
	}
	
	public double getPx() {
		return px;
	}

	public void setPx(double px) {
		this.px = px;
	}

	public double getPy() {
		return py;
	}

	public void setPy(double py) {
		this.py = py;
	}

	public double getV() {
		return v;
	}

	public void setV(double v) {
		this.v = v;
	}

	public Color getC() {
		return c;
	}

	public void setC(Color c) {
		this.c = c;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public int getB() {
		return b;
	}
	
}
