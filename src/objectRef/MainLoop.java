package objectRef;

import java.io.IOException;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;


public class MainLoop extends TimerTask{

	
	private Game game;
	
	public MainLoop(Game game)
	{
		this.game=game;
	}
	
	
	@Override
	public void run()
	{
		try {
			game.update();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	try
	{
		SwingUtilities.invokeLater(new Runnable()
				{

					@Override
					public void run() {
						// TODO Auto-generated method stub
						game.repaint();
					}
			
				});
	} catch(Exception ex)
	{
		Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
	}
	}
}
