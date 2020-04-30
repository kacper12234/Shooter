package objectRef;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.Iterator;
import java.util.Random;


public class Enemy extends Object{

	private int ws=game.getWidth();
	private int hs=game.getHeight();
	private double vy;
	private long timea=System.currentTimeMillis();
	private Random rand=new Random();
	private Handler handler;
	private ImageLoader img;
	
	
	public Enemy(double px, int hp,Game game,ImageLoader img) throws IOException {
		super((int)(game.getWidth()*0.07),game.getWidth()/20, px, game.getHeight()/2, hp,Type.Basic, game);
		vy=0;
		handler=game.getHandler();
		this.img=img;
	}

	@Override
	public void update() {
		if(getPy()>hs*0.3125)
			setPy(getPy()-hs*0.000125);
		else
			move();
		
		shot();
		collision();
	}
	
	private void collision()
	{
		for(Iterator<PlayerBullet> it=handler.getPlayerBullets().iterator();it.hasNext();)
		{
			PlayerBullet pb=it.next();
			if(getBounds().intersects(pb.getBounds()))
			{
				setHp(getHp()-1);
				it.remove();
			}
			else if(pb.getPy()>hs*0.45625)
				it.remove();
		}
		if(getBounds().intersects(handler.getSquare().get(0).getBounds()))
			handler.getSquare().get(0).setHp(handler.getSquare().get(0).getHp()-1);
	}
	
	private void shot()
	{
long timeb=System.currentTimeMillis();
		
		if(this==handler.getSquare().get(1) && getPy()<hs*0.45625)
		if(timeb-timea>1000/Math.sqrt(handler.getSquare().size()*handler.getLevel()))
		{
			int i=rand.nextInt(handler.getSquare().size()-1)+1;
			handler.addEnemyBullet(new EnemyBullet(handler.getSquare().get(i).getPx(),handler.getSquare().get(i).getPy(),0,game));
			timea=System.currentTimeMillis();
		}
	}
	
	private void move()
	{
		if(handler.getLevel()%5>=3 || handler.getSquare().size()==2)
		{
			if(this==handler.getSquare().get(handler.getSquare().size()-1))
			if(getPx()+getA()/2>hs*0.4375 || handler.getVx()==0)
					handler.setVx(-ws*0.00114);
				else if(handler.getSquare().get(1).getPx()-getA()/2<-hs*0.4375)
					handler.setVx(ws*0.00114);
			setPx(getPx()+handler.getVx());
		}
		
		if(handler.getW()==-1 && handler.getSquare().size()>2 && getPy()<=hs*0.3125 && (handler.getLevel()%5==2 || handler.getLevel()%5==4 ))
		{
			do
			handler.setW(rand.nextInt(handler.getSquare().size()-1)+1);
			while(handler.getW()==handler.getTm());
		}
		
		if(handler.getW()!=-1 && this==handler.getSquare().get(handler.getW()))
		{
			if(vy==0)
				vy=-hs*0.0025;
			if(getPy()>-hs*0.1 && vy<0)
			{
					setPy(getPy()+vy);
			}
			else
			{
				vy=hs*0.0025;
				setPy(getPy()+vy);
				if(getPy()>=hs*0.3125)
				{
					setPy(hs*0.3125);
					vy=0;
					handler.setTm(handler.getW());
					handler.setW(-1);
				}
			}
		}
	}
	
	

	@Override
	public void render(Graphics g) {
		g.drawImage(img,(int) getPx() - getA()/ 2,(int) getPy() - getB() / 2,null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) getPx() - getA() / 2, (int) getPy() - getB() / 2,  getA(),  getB());
	}

}
