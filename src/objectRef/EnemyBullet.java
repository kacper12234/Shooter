package objectRef;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class EnemyBullet extends Object{

	private double b;
	Handler handler;
	
	public EnemyBullet( double px, double py,double v, Game game) {
		super(game.getWidth()*0.0043, px, py,v, game);
		b=game.getHeight()*0.0125;
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
		g.fillRect((int) (getPx() - getA() / 2), (int) (getPy() - b / 2), (int) getA(), (int) b);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) (getPx() - getA() / 2), (int) (getPy() - b / 2), (int) getA(), (int) b);
	}

}
