package objectRef;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.swing.JFrame;


public class Game extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BufferedImage bi;
	private Handler handler;
	private int ws,hs;
	
	public Game()
	{	
		Dimension size=Toolkit.getDefaultToolkit().getScreenSize();
		hs=(int) (size.getHeight()*0.74);
	setSize((int) (hs*0.875),hs);
	setTitle("Shooter");
	setResizable(false);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
		ws=getWidth();
		bi=new BufferedImage(ws,hs,BufferedImage.TYPE_INT_ARGB);
		handler=new Handler();
		this.addKeyListener(new KeyInput(this));
	}
	
	public static void main(String []args)
	{
	java.awt.EventQueue.invokeLater(new Runnable()
			{
		public void run()
		{
			new Game().setVisible(true);
		}
			});
	}

	@Override
	public void paint(Graphics g)
	{
		draw(bi.getGraphics());
		g.drawImage(bi,0,0,null);;
	}
	
	public void draw(Graphics g)
	{
		((Graphics2D) g).setBackground(Color.white);
		g.clearRect(0, 0,ws, hs);
		g.translate(ws/2, hs/2);
		((Graphics2D) g).scale(1, -1);
		handler.render(g);
		String in="PRESS SPACE TO START";
		String cn1="WASD - move",cn2="SPACE - shot";
		Font f=new Font("Monospaced", Font.BOLD, (int) (hs*0.03));
		Graphics2D g2=(Graphics2D) g;
	     g2.scale(-1, 1);
	     g2.rotate(3.14);
	     g2.setFont(f);
	     g2.setColor(Color.BLACK);
	        if(handler.getSquare().size()==0)
	        {
	        	g2.drawString(in, (int) (-ws*0.22), 0);
	        g2.drawString(cn1, (int) (-ws*0.12), (int) (hs*0.1));
	        g2.drawString(cn2, (int) (-ws*0.13), (int) (hs*0.15));
	        }
	        else
	        {
	        	String p="Score: "+String.valueOf(handler.getScore());
	    		String h="HP: "+String.valueOf(handler.getSquare().get(0).getHp());
	    		g2.drawString(p, (int) (-ws/2+ws*0.015),(int) (-hs*0.43));
		        g2.drawString(h, (int) (ws/2-ws*0.12), (int)(-hs*0.43));
	        }
	}
	
	public void update()
	{
		if(handler.getLevel()==0)
			handler.addObject(new Player(this));
			
		if(handler.getSquare().size()==1)
		{
			handler.setLevel(handler.getLevel()+1);
			loadLevel();
		}
			
		handler.update();
	}
	
	public void loadLevel()
	{
		Random rand=new Random();
		Color enm=new Color(rand.nextInt(0x1000000));
		if(handler.getLevel()%5!=0)
		for(int i=0;i<8;i++)
			handler.addObject(new Enemy(-ws*0.378+ws*0.107*i,20*(int)Math.sqrt(handler.getLevel()),enm,this));
		else
			handler.addObject(new Boss(40*handler.getLevel(),enm,this));
	}

	public Handler getHandler() {
		return handler;
	}


	
}
