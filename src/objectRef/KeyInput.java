package objectRef;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;

public class KeyInput extends KeyAdapter{

	Game game;
	Handler handler;
	
	public KeyInput(Game game)
	{
		this.game=game;
		handler=game.getHandler();
	}
	
	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_W:
		handler.setUp(true);
		break;
		case KeyEvent.VK_S:
		handler.setDown(true);
		break;
		case KeyEvent.VK_A:
		handler.setLeft(true);
		break;
		case KeyEvent.VK_D:
		handler.setRight(true);
		break;
		case KeyEvent.VK_SPACE:
			if(handler.getSquare().size()>0)
				handler.setShot(true);
			else
			{
				handler.setLevel(0);
				handler.setW(-1);
				handler.setTm(-1);
				handler.setScore(0);
				handler.setT(new Timer());
				handler.getT().scheduleAtFixedRate(new MainLoop(game), 0, 10);
			}
		break;
		}	
	}
	
	public void keyReleased(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_W:
		handler.setUp(false);
		break;
		case KeyEvent.VK_S:
		handler.setDown(false);
		break;
		case KeyEvent.VK_A:
		handler.setLeft(false);
		break;
		case KeyEvent.VK_D:
		handler.setRight(false);
		break;
		case KeyEvent.VK_SPACE:
			handler.setShot(false);
		break;
		}
	}
	
}
