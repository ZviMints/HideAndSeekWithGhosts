package Player;
import javax.swing.JOptionPane;

import myFrame.GamePanel;
import myFrame.Menu;

public class Animate extends Thread {
	private GamePanel g;
	private Player player;
	public Animate(GamePanel g, Player player)
	{
		this.g = g;
		this.player = player;
	}
	@Override
	public void run() {
		while(!g.getFruitsList().isEmpty())
		{
			try { Thread.sleep(50);} // The animation wont run too fast				 
			catch (InterruptedException e) {} 
			if(player.ang <= 360)
				g.update();	
		}
		g.GameMode = false;
		g.play.stop();
		Menu.SetVisableTrue();
		JOptionPane.showMessageDialog(null, "Whooho!\nAll Fruits have Eaten!");
	}
}
