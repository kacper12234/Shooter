package objectRef;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Timer;


public class Handler {

	ArrayList <Object> object=new ArrayList<>();
	ArrayList <PlayerBullet> playerBullets=new ArrayList<>();
	ArrayList <EnemyBullet> enemyBullets=new ArrayList<>();
	private boolean up,down,right,left,shot=false;
	private int w,tm,level,score;
	private Timer t;
	private double vx=0;
	
	public double getVx() {
		return vx;
	}

	public void setT(Timer t) {
		this.t = t;
	}

	public void update()
	{	
		for(Object ob: object)
			ob.update();
		for(Object bl: playerBullets)
			bl.update();
		for(Object ebl: enemyBullets)
			ebl.update();
		removeDeadEnemy();
	}
	
	public void render(Graphics g)
	{
		for(Object ob: object)
			ob.render(g);
		for(Object bl: playerBullets)
			bl.render(g);
		for(Object ebl: enemyBullets)
			ebl.render(g);
	}
	
	public void removeDeadEnemy()
	{
		ListIterator<Object> it=object.listIterator();
		if(it.hasNext())
		{
		Object sq=it.next();
		if(sq.getHp()<=0)
		{
			object.clear();
			playerBullets.clear();
			enemyBullets.clear();
			t.cancel();
		}
		else
		while(it.hasNext()){
			sq=it.next();
				if(sq.getHp()<=0)
				{
					if(sq.getType()==Type.Basic)
						score+=5*level;
					else
						score+=50*level;
					if(w!=-1)
					if(sq==object.get(w))
						w=-1;
					else if(w>=it.nextIndex())
						w--;
					it.remove();
				}
			}
		}
	}
	
	public void addObject(Object nS)
	{
		object.add(nS);
	}

	public void addBullet(PlayerBullet nB)
	{
		playerBullets.add(nB);
	}
	
	public void removeBullet(PlayerBullet nB)
	{
		playerBullets.remove(nB);
	}
	
	public void addEnemyBullet(EnemyBullet nB)
	{
		enemyBullets.add(nB);
	}
	
	public void removeEnemyBullet(EnemyBullet nB)
	{
		enemyBullets.remove(nB);
	}
	
	public void setUp(boolean up) {
		this.up = up;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public void setShot(boolean shot) {
		this.shot = shot;
	}

	public boolean isUp() {
		return up;
	}

	public boolean isDown() {
		return down;
	}

	public boolean isRight() {
		return right;
	}

	public boolean isLeft() {
		return left;
	}

	public boolean isShot() {
		return shot;
	}

	public ArrayList<Object> getSquare() {
		return object;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public ArrayList<PlayerBullet> getPlayerBullets() {
		return playerBullets;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int pt) {
		this.score = pt;
	}

	public ArrayList<EnemyBullet> getEnemyBullets() {
		return enemyBullets;
	}

	public Timer getT() {
		return t;
	}

	public int getTm() {
		return tm;
	}

	public void setTm(int tm) {
		this.tm = tm;
	}

	public void setVx(double vx) {
		this.vx = vx;
	}
	
}
