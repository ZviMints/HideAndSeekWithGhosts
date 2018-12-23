/**
 * This class represent Game which include Fruits List and Pacmans List
 * this class can init Pacmans and Fruits from Matrix
 * @author Tzvi Mints and Or Abuhazira
 */
package Game;
import java.util.ArrayList;
import java.util.List;

import Box.Box;
import File_format.CSVToMatrix;
import Fruit.Fruit;
import Geom.Point3D;
import Ghost.Ghost;
import Pacman.Pacman;
import Player.Player;
public class Game{

	/* * * * * * * * * * * * * *  Initialization Variables * * * * * * * * * * * * * * * */
	private List<Pacman> Pacman_List;
	private List<Fruit> Fruit_List;
	private List<Ghost> Ghost_List;
	private List<Box> Box_List;
	private Player player;

	/* * * * * * * * * * * * * * * * * * Setters and Getters * * * * * * * * * * * * * * * */
	public List<Pacman> getPacmanList() { return Pacman_List; }
	public List<Fruit> getFruitList() { return Fruit_List; }
	public List<Ghost> getGhostList() { return Ghost_List; }
	public List<Box> getBoxList() { return Box_List; }
	public Player getPlayer() { return this.player; }


	/* * * * * * * * * * * * * * * * * * Constructor * * * * * * * * * * * * * * * */
	public Game() {
		// ************ initialize Set ************ //
		Pacman_List = new ArrayList<Pacman>();
		Fruit_List = new ArrayList<Fruit>();
		Ghost_List = new ArrayList<Ghost>();
		Box_List = new ArrayList<Box>();
		this.player = null;
	}
	public Game(String path)
	{
		// ************ initialize Set ************ //
		Pacman_List = new ArrayList<Pacman>();
		Fruit_List = new ArrayList<Fruit>();
		Box_List = new ArrayList<Box>();
		Ghost_List = new ArrayList<Ghost>();


		// ************ initialize Sets ************ //
		CSVToMatrix cr = new CSVToMatrix(path);
		for(int i=1; i < cr.getRowsSize(); i++)
		{
			if(cr.getRowAtIndexI(i).get(0).equals("F")) // Its Fruit!
			{
				String id = cr.getRowAtIndexI(i).get(1);
				Point3D p = new Point3D(Double.parseDouble(cr.getRowAtIndexI(i).get(2)) // Latitude
						,Double.parseDouble(cr.getRowAtIndexI(i).get(3)) // Longitude
						,Double.parseDouble(cr.getRowAtIndexI(i).get(4))); // Altitude
				Fruit fruit = new Fruit(id,p);
				Fruit_List.add(fruit);
			}
			else if(cr.getRowAtIndexI(i).get(0).equals("P")) // Its Pacman!
			{
				String id = cr.getRowAtIndexI(i).get(1);
				String speed = cr.getRowAtIndexI(i).get(5);
				String radius = cr.getRowAtIndexI(i).get(6);
				Point3D p = new Point3D(Double.parseDouble(cr.getRowAtIndexI(i).get(2)) // Latitude
						,Double.parseDouble(cr.getRowAtIndexI(i).get(3)) // Longitude
						,Double.parseDouble(cr.getRowAtIndexI(i).get(4))); // Altitude
				Pacman pacman = new Pacman(id,speed, radius, p);
				Pacman_List.add(pacman);	
			}
			else if(cr.getRowAtIndexI(i).get(0).equals("G")) // Its Ghost!
			{
				String id = cr.getRowAtIndexI(i).get(1);
				String speed = cr.getRowAtIndexI(i).get(5);
				String radius = cr.getRowAtIndexI(i).get(6);
				Point3D g = new Point3D(Double.parseDouble(cr.getRowAtIndexI(i).get(2)) // Latitude
						,Double.parseDouble(cr.getRowAtIndexI(i).get(3)) // Longitude
						,Double.parseDouble(cr.getRowAtIndexI(i).get(4))); // Altitude
				Ghost ghost = new Ghost(id,speed, radius, g);
				Ghost_List.add(ghost);	
			}
			else // Its Box!
			{
				String id = cr.getRowAtIndexI(i).get(1);
				Point3D p_bottom = new Point3D(Double.parseDouble(cr.getRowAtIndexI(i).get(2)) // Latitude
						,Double.parseDouble(cr.getRowAtIndexI(i).get(3)) // Longitude
						,Double.parseDouble(cr.getRowAtIndexI(i).get(4))); // Altitude
				Point3D p_upper = new Point3D(Double.parseDouble(cr.getRowAtIndexI(i).get(5)) // Latitude
						,Double.parseDouble(cr.getRowAtIndexI(i).get(6)) // Longitude
						,Double.parseDouble(cr.getRowAtIndexI(i).get(7))); // Altitude
				Box box = new Box(id,p_bottom,p_upper);
				Box_List.add(box);
			}
		}
	}
	/* * * * * * * * * * * * * * * * * * toString * * * * * * * * * * * * * * * */
	public String toString()
	{
		String ans = "Game Data:" + "\n";
		ans += " Player:" + "\n";
		ans += player.toString();
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