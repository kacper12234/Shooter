package objectRef;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class PlayerBullet extends Object{

	private double b;
	Handler handler;
	
	public PlayerBullet(double px, double py,Game game) {
		super(game.getWidth()*0.0043, px, py, 0, game);
		b=game.getHeight()*0.0125;
		handler=game.getHandler();
	}

	@Override
	public void update() {
		setPy(getPy()+game.getHeight()*0.004);
	}

	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.ORANGE.darker());
		g.fillRect((int) (getPx() - getA() / 2), (int) (getPy() - b / 2), (int) getA(), (int) b);
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int) (getPx() - getA() / 2), (int) (getPy() - b / 2), (int) getA(), (int) b);
	}

}
