/**
 * This Class represent Player that implements GIS_Player
 * each Player has Player Data and Geom Element
 * @author Tzvi Mints and Or Abuhazira
 */
package Player;
import Coords.MyCoords;
import Geom.Point3D;

public class Player implements GIS_Player{
	/* * * * * * * * * * * * * *  Initialization Variables * * * * * * * * * * * * * * * */
	private Point3D point;
	private PlayerData PlayerData;
	
	/* * * * * * * * * * * * * * * * * * Setters and Getters * * * * * * * * * * * * * * * */
	public PlayerData getInfo() { return PlayerData; }
	
	/* * * * * * * * * * * * * * * * * * Constructors * * * * * * * * * * * * * * * */
	public Player(String id, Point3D point) { 
		// ************ initialize Geom_element ************ //
		this.point = point;
		// ************ initialize Fruit Data ************ //
		PlayerData = new PlayerData(id);
	}
	public Player(Player p) {
		this.point = p.get3DPoint();
		this.PlayerData = p.PlayerData;
	}

	/* * * * * * * * * * * * * * * * * * toString * * * * * * * * * * * * * * * */
	public String toString()
	{
		String ans = "Player:" + "--> ";
		ans += PlayerData.toString();
		ans +=  "," + "Point3D" + ":" + this.point;
		return ans;
	}
	/* * * * * * * * * * * * * * * * * * Override * * * * * * * * * * * * * * * */
	@Override
	public void translate(Point3D vec) {
		MyCoords coords = new MyCoords();
		point = new Point3D(coords.add(this.point, vec));
	}
	@Override
	public Point3D get3DPoint() {
		return point;
	}
}
