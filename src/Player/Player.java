/**
 * This Class represent Fruit 
 * @author Tzvi Mints and Or Abuhazira
 */
package Player;
import Coords.MyCoords;
import Geom.Point3D;

public class Player {
	/* * * * * * * * * * * * * *  Initialization Variables * * * * * * * * * * * * * * * */
	private Point3D _p; // In [Lat,Lon,Alt]
	private String _id;
	
	public double ang;
	/* * * * * * * * * * * * * *  Constructor * * * * * * * * * * * * * * * */
	public Player(Point3D p, String id)
	{
		this._id = id;
		this._p = p;
	}
	/* * * * * * * * * * * * * *  toString * * * * * * * * * * * * * * * */
	public String toString()
	{
		String ans = "";
		ans += "Player id: " + _id + " Point: " + _p;
		return ans;
	}
	/* * * * * * * * * * * * * *  Transfer * * * * * * * * * * * * * * * */
	public void transfer(Point3D vec)
	{
		MyCoords coords = new MyCoords();
		this._p = coords.add(this._p, vec);
	}
	/* * * * * * * * * * * * * *  Setters and Getters * * * * * * * * * * * * * * * */
	public Point3D getP() {
		return _p;
	}
	public String get_id() {
		return _id;
	}
	public void setP(Point3D _p) {
		this._p = _p;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
}
