package objectRef;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Iterator;

public class Player extends Object{

	private int ws=game.getWidth();
	private int hs=game.getHeight();
	private long timea=System.currentTimeMillis();
	Handler handler;
	
	public Player(Game game) {
		super(game.getWidth()*0.0285, 0,-game.getHeight()/2+50,5, game);
		handler=game.getHandler();
	}

	@Override
	public void update() {
			
		
		if(game.getHandler().isUp() && getPy()+getA()/2<hs/2-32)
			setPy(getPy()+hs*0.002125);
		if(game.getHandler().isDown() && getPy()-getA()/2>-hs/2+2)
			setPy(getPy()-hs*0.002125);
			if(game.getHandler().isRight() && getPx()+getA()/2<ws/2-2)
				setPx(getPx()+hs*0.002125);
			if(game.getHandler().isLeft() && getPx()-getA()/2>-ws/2+2)
				setPx(getPx()-hs*0.002125);
		
			long timeb=System.currentTimeMillis();
			if(game.getHandler().isShot() && timeb-timea>60)
			{
				game.getHandler().addBullet(new PlayerBullet(getPx()-ws*0.0071,getPy()+hs*0.0125/2,game));
				game.getHandler().addBullet(new PlayerBullet(getPx()+ws*0.0071,getPy()+hs*0.0125/2,game));
				timea=System.currentTimeMillis();
			}
			
			collision();
	}

	private void collision()
	{
		for(Iterator<EnemyBullet> it=handler.getEnemyBullets().iterator();it.hasNext();)
		{
			EnemyBullet eb=it.next();
			if(getBounds().intersects(eb.getBounds()))
			{
				setHp(getHp()-1);
				it.remove();
			}
			else if(eb.getPy()<-hs*0.45625)
				it.remove();
		}
	}
	@Override
	public void render(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect((int) (getPx() - getA() / 2), (int) (getPy() - getA() / 2), (int) getA(), (int) getA());
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) (getPx() - getA() / 2), (int) (getPy() - getA() / 2), (int) getA(), (int) getA());
	}

	
}
