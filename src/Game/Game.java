/**
 * This class represent Game which include Fruits List and Pacmans List
 * this class can init Pacmans and Fruits from Matrix
 * @author Tzvi Mints and Or Abuhazira
 */
package Game;
import java.util.ArrayList;
import java.util.List;
import File_format.CSVToMatrix;
import Geom.Point3D;
import Map.Map;
import Objects.Box;
import Objects.Fruit;
import Objects.Ghost;
import Objects.Pacman;
import Player.Player;
public class Game{

	/* * * * * * * * * * * * * *  Initialization Variables * * * * * * * * * * * * * * * */
	private List<Pacman> Pacman_List; // List for Pacman
	private List<Fruit> Fruit_List; // List for Fruit
	private List<Ghost> Ghost_List; // List for Ghost
	private List<Box> Box_List; // List for Box
	private Player player; // Player
	private String path; // Name of path
	/* * * * * * * * * * * * * * * * * * Setters and Getters * * * * * * * * * * * * * * * */
	public List<Pacman> getPacmanList() { return Pacman_List; }
	public List<Fruit> getFruitList() { return Fruit_List; }
	public List<Ghost> getGhostList() { return Ghost_List; }
	public List<Box> getBoxList() { return Box_List; }
	public Player getPlayer() { return this.player; }
	public String getPath() { return path; }
	public void setPath(String path) { this.path = path; }
	/* * * * * * * * * * * * * * * * * * Constructor * * * * * * * * * * * * * * * */
	/**
	 * This method accepts a CSV file name and a player initializes the list of
	 * Pacman, Fruit, Ghost, and Box
	 * @param path
	 */
	public Game(String path) {
		// ************ initialize Set ************ //
		Pacman_List = new ArrayList<Pacman>(); //Initializes the list of the Pacmam 
		Fruit_List = new ArrayList<Fruit>(); //Initializes the list of the Fruit
		Ghost_List = new ArrayList<Ghost>(); //Initializes the list of the Ghost
		Box_List = new ArrayList<Box>(); //Initializes the list of the Box
		this.player = null; // Initializing the player to null
		this.path = path; // Name of Path
		// ************ initialize Sets ************ //
		CSVToMatrix cr = new CSVToMatrix(path);
		for(int i=1; i < cr.getRowsSize(); i++)
		{
			if(cr.getRowAtIndexI(i).get(0).equals("F")) // Its Fruit!
			{
				Fruit fruit = MakeFruit(cr.getRowAtIndexI(i));
				Fruit_List.add(fruit);
			}
			else if(cr.getRowAtIndexI(i).get(0).equals("P")) // Its Pacman!
			{
				Pacman pacman = MakePacman(cr.getRowAtIndexI(i));
				Pacman_List.add(pacman);					
			}
			else if(cr.getRowAtIndexI(i).get(0).equals("G")) // Its Ghost!
			{
				Ghost ghost = MakeGhost(cr.getRowAtIndexI(i));
				Ghost_List.add(ghost);	
			}
			else // Its Box!
			{
				Box box = MakeBox(cr.getRowAtIndexI(i));
				Box_List.add(box);	
			}
		}
	}
	/* * * * * * * * * * * * * * * * * * MakePacman * * * * * * * * * * * * * * * */
	/**
	 * This method accepts a list with data on Pacman 
	 * and returns Pacman with id,speed,radius and coords
	 * @param cr
	 * @return Pacman
	 */
	public Pacman MakePacman(ArrayList<String> cr) {
		String id = cr.get(1); // id of pacman
		double speed = Double.parseDouble(cr.get(5)); // Speed of pacmen
		double radius = Double.parseDouble(cr.get(6)); // Radius of pacmen
		Point3D p = new Point3D(Double.parseDouble(cr.get(2)) // Latitude
				,Double.parseDouble(cr.get(3)) // Longitude
				,Double.parseDouble(cr.get(4))); // Altitude
		return new Pacman(p,id,speed,radius);
	}
	/* * * * * * * * * * * * * * * * * * MakeBox * * * * * * * * * * * * * * * */
	/**
	 * This method accepts a list with data on the Box 
	 * and returns a box with id , and two coords
	 * @param cr
	 * @return Box
	 */
	public Box MakeBox(ArrayList<String> cr) {
		String id = cr.get(1); // id of Box
		Point3D p0 = new Point3D(Double.parseDouble(cr.get(2)) // Latitude
				,Double.parseDouble(cr.get(3)) // Longitude
				,Double.parseDouble(cr.get(4))); // Altitude
		Point3D p1 =new Point3D(Double.parseDouble(cr.get(5)) // Latitude
				,Double.parseDouble(cr.get(6)) // Longitude
				,Double.parseDouble(cr.get(7))); // Altitude

		return new Box(id, p0 , p1);
	}
	/* * * * * * * * * * * * * * * * * * MakeGhost * * * * * * * * * * * * * * * */
	/**
	 * This method accepts a list with data on Ghost 
	 * and returns Ghost with id,speed,radius and points
	 * @param cr
	 * @return Ghost
	 */
	public Ghost MakeGhost(ArrayList<String> cr) {
		String id = cr.get(1); // id of Ghost
		double speed = Double.parseDouble(cr.get(5)); // Speed of Ghost
		double radius = Double.parseDouble(cr.get(6)); // Radius of Ghost
		Point3D g = new Point3D(Double.parseDouble(cr.get(2)) // Latitude
				,Double.parseDouble(cr.get(3)) // Longitude
				,Double.parseDouble(cr.get(4))); // Altitude
		return new Ghost(g,id,speed, radius);
	}
	/* * * * * * * * * * * * * * * * * * MakeFruit * * * * * * * * * * * * * * * */
	/**
	 * This method accepts a list with data on Fruit 
	 * and returns a fruit with a id and a coords
	 * @param cr
	 * @return Fruit
	 */
	public Fruit MakeFruit(ArrayList<String> cr) {
		String id = cr.get(1); // id of Fruit
		Point3D p = new Point3D(Double.parseDouble(cr.get(2)) // Latitude
				,Double.parseDouble(cr.get(3)) // Longitude
				,Double.parseDouble(cr.get(4))); // Altitude
		return new Fruit(p,id);
	}
	/* * * * * * * * * * * * * * * * * * toString * * * * * * * * * * * * * * * */
	public String toString()
	{
		String ans = "Game Data:" + "\n";
		ans += Pacman_List.size() + " Pacmans:" + "\n";
		ans += Pacman_List.toString();
		ans += Fruit_List.size() + " Fruits:" + "\n";
		ans += Fruit_List.toString();
		ans += Ghost_List.size() + " Ghosts:" + "\n";
		ans += Ghost_List.toString();
		ans += Box_List.size() + " Boxs:" + "\n";
		ans += Box_List.toString();
		return ans;
	}
}