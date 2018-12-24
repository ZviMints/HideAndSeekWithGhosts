package Player;
import Geom.Point3D;
import myFrame.GamePanel;

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
			try { Thread.sleep(30);} // The animation wont run too fast				 
			catch (InterruptedException e) {e.printStackTrace();} 
			g.update();
				
		}
		g.started = false;
	}
}
