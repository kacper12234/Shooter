package objectRef;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.Iterator;


public class Player extends Object{

	private int ws=game.getWidth();
	private int hs=game.getHeight();
	private long timea=System.currentTimeMillis();
	private Handler handler;
	private ImageLoader img;
	
	public Player(Game game,ImageLoader img) throws IOException {
		super(game.getWidth()/25,(int)(game.getWidth()*0.043), 0,-game.getHeight()/2+50,5, game);
		handler=game.getHandler();
		this.img=img;
	}

	@Override
	public void update() {
			
		
		if(game.getHandler().isUp() && getPy()+getB()/2<hs/2-32)
			setPy(getPy()+hs*0.002125);
		if(game.getHandler().isDown() && getPy()-getB()/2>-hs/2+2)
			setPy(getPy()-hs*0.002125);
			if(game.getHandler().isRight() && getPx()+getA()/2<ws/2-2)
				setPx(getPx()+hs*0.002125);
			if(game.getHandler().isLeft() && getPx()-getA()/2>-ws/2+2)
				setPx(getPx()-hs*0.002125);
		
			long timeb=System.currentTimeMillis();
			if(game.getHandler().isShot() && timeb-timea>60)
			{
				game.getHandler().addBullet(new PlayerBullet(getPx()-ws*0.0088,getPy()+hs*0.0125/2,game));
				game.getHandler().addBullet(new PlayerBullet(getPx()+ws*0.0088,getPy()+hs*0.0125/2,game));
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
		g.drawImage(img,(int) getPx() - getA()/ 2,(int) getPy() - getB() / 2,null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) getPx() - getA() / 2, (int) getPy() - getB() / 2, getA(),  getB());
	}

	
}
