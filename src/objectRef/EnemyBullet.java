package objectRef;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class EnemyBullet extends Object{

	Handler handler;
	
	public EnemyBullet( double px, double py,double v, Game game) {
		super((int)(game.getWidth()*0.0043),(int)(game.getHeight()*0.0125), px, py,v, game);
		handler=game.getHandler();
	}

	@Override
	public void update() {
		 setPy(getPy()-game.getHeight()*0.0025);
		setPx(getPx()+getV());
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.PINK.darker());
		g.fillRect((int)getPx() - getA() / 2,(int)getPy() - getB() / 2, getA(), getB());
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)getPx() - getA() / 2, (int)getPy() - getB()/ 2, getA(), getB());
	}

}
