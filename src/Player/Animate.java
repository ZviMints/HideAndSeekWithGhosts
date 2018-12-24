package Player;
import Geom.Point3D;
import myFrame.GamePanel;

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
			try { Thread.sleep(15);} // The animation wont run too fast				 
			catch (InterruptedException e) {e.printStackTrace();} 
			
			Point3D vec = player.getVec();
			if( vec != null )
			{
				player.transfer(vec);
				g.update();
			}
		}
		g.started = false;
		String info = g.play.getStatistics();
		System.out.println(info);
	}
}
