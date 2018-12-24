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
	private List<Pacman> Pacman_List;
	private List<Fruit> Fruit_List;
	private List<Ghost> Ghost_List;
	private List<Box> Box_List;
	private Player player;
	private Map map;

	/* * * * * * * * * * * * * * * * * * Setters and Getters * * * * * * * * * * * * * * * */
	public List<Pacman> getPacmanList() { return Pacman_List; }
	public List<Fruit> getFruitList() { return Fruit_List; }
	public List<Ghost> getGhostList() { return Ghost_List; }
	public List<Box> getBoxList() { return Box_List; }
	public Player getPlayer() { return this.player; }


	/* * * * * * * * * * * * * * * * * * Constructor * * * * * * * * * * * * * * * */
	public Game(String path, Map map) {
		// ************ initialize Set ************ //
		Pacman_List = new ArrayList<Pacman>();
		Fruit_List = new ArrayList<Fruit>();
		Ghost_List = new ArrayList<Ghost>();
		Box_List = new ArrayList<Box>();
		this.player = null;
		this.map = map;

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
	private Pacman MakePacman(ArrayList<String> cr) {
		String id = cr.get(1);
		double speed = Double.parseDouble(cr.get(5));
		double radius = Double.parseDouble(cr.get(6));
		Point3D p = new Point3D(Double.parseDouble(cr.get(2)) // Latitude
				,Double.parseDouble(cr.get(3)) // Longitude
				,Double.parseDouble(cr.get(4))); // Altitude
		return new Pacman(p,id,speed,radius);
	}
	/* * * * * * * * * * * * * * * * * * MakeBox * * * * * * * * * * * * * * * */
	private Box MakeBox(ArrayList<String> cr) {
		String id = cr.get(1);
		Point3D p0 = new Point3D(Double.parseDouble(cr.get(2)) // Latitude
				,Double.parseDouble(cr.get(3)) // Longitude
				,Double.parseDouble(cr.get(4))); // Altitude
		Point3D p1 = new Point3D(Double.parseDouble(cr.get(5)) // Latitude
				,Double.parseDouble(cr.get(6)) // Longitude
				,Double.parseDouble(cr.get(7))); // Altitude
		int width = (int) (p1.x() - p0.x());
		int height = (int) (p0.y() - p1.y());
		return new Box(id,(int)p0.x(),(int)p1.y(),width,height);
	}
	/* * * * * * * * * * * * * * * * * * MakeGhost * * * * * * * * * * * * * * * */
	private Ghost MakeGhost(ArrayList<String> cr) {

		String id = cr.get(1);
		double speed = Double.parseDouble(cr.get(5));
		double radius = Double.parseDouble(cr.get(6));
		Point3D g = new Point3D(Double.parseDouble(cr.get(2)) // Latitude
				,Double.parseDouble(cr.get(3)) // Longitude
				,Double.parseDouble(cr.get(4))); // Altitude
		return new Ghost(g,id,speed, radius);
	}
	/* * * * * * * * * * * * * * * * * * MakeFruit * * * * * * * * * * * * * * * */
	private Fruit MakeFruit(ArrayList<String> cr) {
		String id = cr.get(1);
		Point3D p = new Point3D(Double.parseDouble(cr.get(2)) // Latitude
				,Double.parseDouble(cr.get(3)) // Longitude
				,Double.parseDouble(cr.get(4))); // Altitude
		return new Fruit(p,id);
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