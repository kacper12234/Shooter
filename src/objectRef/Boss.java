package objectRef;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.Iterator;
import java.util.Random;

import javax.imageio.ImageIO;

public class Boss extends Object{

	private int totalHp;
	int hs=game.getHeight();
	int ws=game.getWidth();
	private double vx,vr;
	private int sh=4;
	private Random rand=new Random();
	Handler handler;
	private long timea=System.currentTimeMillis();
	private ImageLoader convimg;
	private Image img;
	
	public Boss(int hp,Color c, Game game) throws IOException {
		super((int)(game.getWidth()*0.2),(int)(game.getWidth()*0.12), 0, game.getHeight()/2, hp, Type.Boss, game);
		totalHp=hp;
		vx=ws*0.00114;
		handler=game.getHandler();
		convimg=new ImageLoader(ImageIO.read(getClass().getResource("/boss.png")),c);
		img=convimg.getScaledInstance(getA(), getB(), Image.SCALE_SMOOTH);
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
		g.drawImage(img,(int) getPx() - getA()/ 2,(int) getPy() - getB() / 2,null);
		drawHBar(g);
	}
	
	public void drawHBar(Graphics g)
	{
		g.setColor(Color.RED);
		g.fillRect((int) getPx() - getA() / 2, (int) getPy() + getB() / 2 + hs/100, getA()*getHp()/totalHp, hs/50);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) getPx() - getA() / 2, (int) getPy() - getB() / 2, getA(), getB());
	}

}
