package Player;
import myFrame.GamePanel;
import myFrame.Menu;

public class Animate extends Thread {
	private GamePanel g;
	public Animate(GamePanel g)
	{
		this.g = g;
	}
	@Override
	public void run() {
		while(!g.getFruitsList().isEmpty())
		{
			try { Thread.sleep(50);} // The animation wont run too fast				 
			catch (InterruptedException e) {} 
			g.update();
				
		}
		g.GameMode = false;
		g.play.stop();
		Menu.SetVisableTrue();
	}
}
