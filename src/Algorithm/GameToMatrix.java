/**
 * This Class is responsible to convert from Game to Matrix
 * used for taking steps in the Algorithm
 * for example :
 * [  G       ]
 * [      BBBB]
 * [      B   ]
 * [ F    B   ]
 * @author Tzvi Mints and Or Abuhazira
 * @version 4.0
 */
package Algorithm;
import java.util.Arrays;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import Geom.Point3D;
import Map.Map;
import Objects.Box;
import Objects.Fruit;
import Objects.Ghost;
import Objects.Pacman;
import Player.Player;

public class GameToMatrix {
	private static GameToMatrix matrix; // is the current Object
	static char[][] mat; // is the Matrix[][] that represent the Game
	static JFrame frame; // the PopUP frame for the Graph
	static JTextArea ta; // The PopUP textarea for the Graph
	/* * * * * * * * * * * * * * * * * * Singleton * * * * * * * * * * * * * * * */
	public static GameToMatrix Singleton(Player player, List<Fruit> FruitsList, List<Box> BoxsList, List<Ghost> GhostsList, List<Pacman> PacmansList, Map map) 
	{ 
		// To Ensure Only One Instance Is Created 
		if (mat == null) 
		{
			matrix = new GameToMatrix(player,FruitsList,BoxsList,GhostsList,PacmansList,map); 
			return matrix; // Return GameToMatrix
		}
		else
			matrix.Update(player,FruitsList,BoxsList,GhostsList,PacmansList,map); 
		return matrix; // Return GameToMatrix
	} 
	/* * * * * * * * * * * * * * * * * * Constructor * * * * * * * * * * * * * * * */
	public GameToMatrix(Player player, List<Fruit> FruitsList, List<Box> BoxsList, List<Ghost> GhostsList, List<Pacman> PacmansList, Map map)
	{
		this.Update(player, FruitsList, BoxsList, GhostsList, PacmansList, map);
	}
	/* * * * * * * * * * * * * * * * * * Update * * * * * * * * * * * * * * * */
	/**
	 * This Method is responsible to get FruitsList,PacmansList and etc and in each
	 * Object put the right sign that will represent the Object, for example
	 * 'P' will represent Pacman
	 * @param player is the Game Player
	 * @param FruitsList is the FruitsList of the Game
	 * @param BoxsList is the BoxsList of the Game
	 * @param GhostsList is the GhostsList of the Game
	 * @param PacmansList is the PacmansList of the Game
	 * @param map is the current map of the game
	 */
	private void Update(Player player, List<Fruit> FruitsList, List<Box> BoxsList, List<Ghost> GhostsList, List<Pacman> PacmansList, Map map) {
		int w = map.getWidth(); // width of the map
		int h = map.getHeight(); // height of the map
		if(mat == null || h != mat.length || w != mat[0].length )
		{
			mat = new char[h][w]; // Create new mat[][] of char's
		}
		for(int i=0; i<mat.length;i++)
			for(int j=0;j<mat[i].length;j++)
				mat[i][j]=' '; // fill the Matrix
		
		for(Pacman pacman : PacmansList) // for all Pacmans
		{
			if(pacman.isAlive()) // if Pacman is Alive
			{
			Point3D p = map.getPixelFromCord(pacman.getP()); // Get the current Object Point
			mat[(int)p.y()][(int)p.x()] = 'P'; // Declare the Object in the Matrix
			}
		}
		for(Fruit fruit : FruitsList)
		{
			if(fruit.isAlive())
			{
			Point3D p = map.getPixelFromCord(fruit.getP()); // Get the current Object Point
			mat[(int)p.y()][(int)p.x()] = 'F'; // Declare the Object in the Matrix
			}
		}
		for(Box b : BoxsList)
		{
			Point3D b_p0 = map.getPixelFromCord(b.getP0()); // Get the current Object Point
			Point3D b_p1 = map.getPixelFromCord(b.getP1()); // Get the current Object Point
			for(int i = (int)b_p0.x() - 3 ; i< (int)b_p1.x() + 3; i++)
			{
				int j2 = (int)b_p1.y();
				int j1 = (int)b_p0.y();
				mat[j1][i] = 'B';
				mat[j2][i] = 'B';
			}

			for(int j = (int)b_p1.y() - 3 ; j < (int)b_p0.y() + 3; j++)
			{
				int i2 = (int)b_p1.x();
				int i1 = (int)b_p0.x();
				mat[j][i1] = 'B';
				mat[j][i2++] = 'B';
			}
		}
		for(Ghost ghost : GhostsList)
		{
			Point3D p = map.getPixelFromCord(ghost.getP()); // Get the current Object Point
			mat[(int)p.y()][(int)p.x()] = 'G'; // Declare the Object in the Matrix
		}
		if(player != null)
		{
			Point3D p = map.getPixelFromCord(player.getP()); // Get the current Object Point
			mat[(int)p.y()][(int)p.x()] = 'M'; // Declare the Object in the Matrix
		}
	}
	/* * * * * * * * * * * * * * * * * * POP UP * * * * * * * * * * * * * * * */
	/**
	 * This Method is responsible to PopUP window that shows the Graph
	 * the Graph represent the current state of the game
	 */
	public void POPUP() {
		if(frame == null)
		{
			frame = new JFrame("State Matrix");
			frame.setLayout(null);
			frame.setVisible(true);
			frame.setResizable(true);
			frame.setBounds(200,20,mat[0].length + 15,mat.length + 35);
			ta = new JTextArea();
			ta.setBounds(0,0,mat[0].length,mat.length);
			ta.setEditable(false);
			frame.setResizable(false);
			JScrollPane sp = new JScrollPane(ta);
			sp.setBounds(0,0,mat[0].length,mat.length);
			frame.add(sp);
			Maze maze = new Maze(mat);
			FindShortestPathFromMat findShortestPathFromMat = new FindShortestPathFromMat();
			List<Coordinate> path = findShortestPathFromMat.SOLVE(maze);
			if(path.isEmpty()) 
				ta.setText(toString());
			else
			{
				ta.setText(maze.ReturnMatWithPath(path));
			}
			frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			frame.addWindowListener(new java.awt.event.WindowAdapter() {
				@Override
				public void windowClosing(java.awt.event.WindowEvent e) {
					frame = null;
					e.getWindow().dispose();
				}
			});
		}
		else
		{
			ta.setText(toString());
		}
	}
	/* * * * * * * * * * * * * * * * * * toString * * * * * * * * * * * * * * * */
	public String toString() {
		String ans = "";
		for (char[] row : mat){
			ans += Arrays.toString(row) + "\n";
		}
		return ans;
	}
	/* * * * * * * * * * * * * * * * * * Get Matrix * * * * * * * * * * * * * * * */
	/**
	 * This method is responsible to return the Matrix
	 */
	public char[][] getMat() {
		return mat;
	}
}
