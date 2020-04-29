package objectRef;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class PlayerBullet extends Object{

	Handler handler;
	
	public PlayerBullet(double px, double py,Game game) {
		super((int)(game.getWidth()*0.0043),(int)(game.getHeight()*0.0125), px, py, 0, game);
		handler=game.getHandler();
	}

	@Override
	public void update() {
		setPy(getPy()+game.getHeight()*0.004);
	}

	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.ORANGE.darker());
		g.fillRect((int) getPx() - getA() / 2, (int) getPy() - getB() / 2, getA(), getB());
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int) getPx() - getA() / 2, (int) getPy() -getB() / 2, getA(), getB());
	}

}
