package objectRef;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Iterator;
import java.util.Random;

public class Boss extends Object{

	private int totalHp;
	int hs=game.getHeight();
	int ws=game.getWidth();
	private double vx,vr;
	private Color c;
	private int sh=4;
	private Random rand=new Random();
	Handler handler;
	private long timea=System.currentTimeMillis();
	
	public Boss(int hp,Color c, Game game) {
		super(game.getWidth()*0.1143, 0, game.getHeight()/2, hp, Type.Boss, game);
		totalHp=hp;
		this.c=c;
		vx=ws*0.00114;
		handler=game.getHandler();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(getPy()>hs*0.3125)
			setPy(getPy()-hs*0.000125);
		else
		{
		if(getPx()+getA()/2>hs*0.4375)
			vx=-ws*0.00114;
		else if(getPx()-getA()/2<-hs*0.4375)
			vx=ws*0.00114;
		setPx(getPx()+vx);
		}
		shot();
		collision();
		
	}

	public void shot()
	{
		long timeb=System.currentTimeMillis();
		if(timeb-timea>1000/(Math.sqrt(handler.getLevel()/2)))
		{
		if(sh==4)
		{
			do
		vr=(double)(rand.nextInt(handler.getLevel())-handler.getLevel()/2)/7;
			while(Math.abs(vr)>0.1 && Math.abs(vr)<handler.getLevel()/17);
		sh=0;
		}
		for(int i=1;i<=handler.getLevel()/2;i++)
			if(i%2!=0)
		handler.addEnemyBullet(new EnemyBullet(getPx()-i,getPy(),vr-(i-1)*0.04,game));
			else
				handler.addEnemyBullet(new EnemyBullet(getPx()-i,getPy(),vr+i*0.04,game));
		sh++;
		timea=System.currentTimeMillis();
		}
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
	}
	
	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(c);
		g.fillRect((int) (getPx() - getA()/ 2), (int) (getPy() - getA() / 2), (int) getA(), (int) getA());
		drawHBar(g);
	}
	
	public void drawHBar(Graphics g)
	{
		g.setColor(Color.RED);
		g.fillRect((int) (getPx() - getA() / 2), (int) (getPy() + getA() / 2 + hs*0.01), (int) (getA()*getHp()/totalHp), (int) (hs*0.02));
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) (getPx() - getA() / 2), (int) (getPy() - getA() / 2), (int) getA(), (int) getA());
	}

}
