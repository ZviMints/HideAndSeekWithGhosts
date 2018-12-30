package myFrame;

import java.util.List;

import javax.swing.JOptionPane;
import Algorithm.FindShortestPathFromMat;
import Algorithm.Coordinate;
import Algorithm.GameToMatrix;
import Algorithm.Maze;
import Coords.MyCoords;
import GUI.Menu;
import Geom.Point3D;
import Player.Player;
import Robot.Play;

public class AlgoThread extends Thread{
	private GamePanel g;
	private Play play;
	private Player player;
	private static final MyCoords coords = new MyCoords();

	public AlgoThread(GamePanel g)
	{
		this.g = g;
		this.play = g.getPlay();
		this.player = g.getPlayer();
	}
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
			try { Thread.sleep(20);} // The animation wont run too fast				 
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
	private Player GetStartPointToPlayer() 
	{
		if(!g.getPacmansList().isEmpty() )
			return new Player(new Point3D(g.getFruitsList().get(0).getP().x(),g.getFruitsList().get(0).getP().y(),0),"Robot");
		else
			return new Player(new Point3D(g.getPacmansList().get(0).getP().x(),g.getPacmansList().get(0).getP().y(),0),"Robot");
	}
}

