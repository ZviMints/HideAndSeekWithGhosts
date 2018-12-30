/**
 * This Class is responsible for the "Client" algorithm side, this is thread that make all the choices 
 * for each step in the game
 * @version 4.0
 * @author Tzvi Mints and Or Abuhazira
 */
package myFrame;
import java.util.List;
import javax.swing.JOptionPane;
import Algorithm.FindShortestPathFromMat;
import Algorithm.Coordinate;
import Algorithm.GameToMatrix;
import Algorithm.Maze;
import GUI.Menu;
import Geom.Point3D;
import Player.Player;
import Robot.Play;

public class AlgoThread extends Thread{
	/* * * * * * * * * * * * * * * * * * Private CONSTANTS * * * * * * * * * * * * * * * */	
	private GamePanel g; // The Panel of the Game
	private Play play; // The Server Side Player
	private Player player; // My Player

	/* * * * * * * * * * * * * * * * * * Constructors * * * * * * * * * * * * * * * */	
	/**
	 * This is the Main Constructor of the AlgoThread,
	 * @param g is the Panel, from which we initialize player & play
	 */
	public AlgoThread(GamePanel g)
	{
		this.g = g; // The Panel of the Game
		this.play = g.getPlay(); // The Server Side Player
		this.player = g.getPlayer(); // My Player
	}
	/* * * * * * * * * * * * * * * * * * @Override - Run * * * * * * * * * * * * * * * */
	/**
	 * This is the main thread run function, in which we control the Panel of the game and move
	 * our player
	 */
	public void run()
	{
		// If user did not INITIALIZE player by click
		if(player == null)
		{
			player = GetStartPointToPlayer();
			g.setPlayer(player);
		}
		// Set Player
		play.setInitLocation(player.getP().x(),player.getP().y());

		// Start game
		play.start();

		// Set Game Mode's to true
		g.setGameMode(true);
		g.setAlgoMode(true);

		// While game is running
		while(play.isRuning())
		{
			// An object GameToMatrix was designed to represent the current game
			// that appears in the GUI for an matrix that is the size of pixels of the screen
			GameToMatrix mat = GameToMatrix.Singleton(player,g.getFruitsList(),g.getBoxsList(),g.getGhostsList(),g.getPacmansList(),g.getMap());

			// Converts a 2D matrix into an object that is capable of performing operations 
			// and checking the locations of objects in the matrix
			Maze maze = Maze.Singleton(mat.getMat());  

			// Get Shortest Path from current MAZE
			FindShortestPathFromMat findShortestPathFromMat = new FindShortestPathFromMat();
			List<Coordinate> path = findShortestPathFromMat.SOLVE(maze);

			if(!path.isEmpty()) path.remove(0); // FIRST ONE

			// SLEEP
			try { Thread.sleep(50);} // The animation wont run too fast				 
			catch (InterruptedException e) {} 

			// Find the current Angle that the player need to move
			Coordinate dist = path.get(1);
			path.remove(0);

			Coordinate src = dist.getPred();
			double dx = dist.getY() - src.getY();
			double dy = dist.getX() - src.getX();
			player.ang = getAngle(dx,dy);

			// Update the GUI
			g.update();	
		}
		g.update();	

		JOptionPane.showMessageDialog(null, "Algo Finished");
		Menu.SetVisableTrue();
		g.setGameMode(false);
		g.setAlgoMode(false);
	}
	/* * * * * * * * * * * * * * * * * * getAngle * * * * * * * * * * * * * * * */
	//NOTE: We could use Enumum's or Final Consts but we prefered the follow type of form
	/**
	 * This Function is responsible to convert from DIRECTIONS such that (-1,0) and etc.. to Degrees
	 * @param dx is the x in the vector (x,y)
	 * @param dy is the y in the vector (x,y)
	 * @return Degrees (°) 
	 */
	private double getAngle(double dx, double dy) {
		if( dx == 0 && dy == 1) // {0,1}
			return 180;
		else if( dx == 1 && dy == 1) // {1,1}
			return  135;
		else if( dx == 1 && dy == 0) // {1,0}
			return 90;
		else if(dx == 1 && dy == -1 ) // {1,-1}
			return 45;
		else if(dx == 0 && dy == -1 ) //  {0,-1}
			return 360;
		else if( dx == -1 && dy == -1) // {-1,-1}
			return 315;
		else if( dx == -1 && dy == 0) // {-1,0}	
			return 270;
		else // {-1,1}
			return 225;
	}
	/* * * * * * * * * * * * * * * * * * GetStartPointToPlayer * * * * * * * * * * * * * * * */
	/**
	 * This method is responsible to Return the Algo Choice for the start Point of the Play 
	 * @return Player with START Location
	 */
	private Player GetStartPointToPlayer() 
	{
		if(!g.getPacmansList().isEmpty() )  // Put in first Fruit Place
			return new Player(new Point3D(g.getFruitsList().get(0).getP().x(),g.getFruitsList().get(0).getP().y(),0),"Robot");
		else // Puts in first Pacman Place
			return new Player(new Point3D(g.getPacmansList().get(0).getP().x(),g.getPacmansList().get(0).getP().y(),0),"Robot");
	}
}

