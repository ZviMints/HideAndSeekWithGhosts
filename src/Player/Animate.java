package Player;
import myFrame.GamePanel;

public class Animate implements Runnable {
	GamePanel g;
	Animate(GamePanel g)
	{
		this.g = g;
	}
	@Override
	public void run() {
		while(true)
		{
			g.update();
			try 
			{
				Thread.sleep(10); // The animation wont run too fast
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			} 	
		}
	}
}
