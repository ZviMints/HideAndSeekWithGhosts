/**
 * This Class represent Fruit 
 * @author Tzvi Mints and Or Abuhazira
 */
package Objects;
import Geom.Point3D;

public class Fruit {
	/* * * * * * * * * * * * * *  Initialization Variables * * * * * * * * * * * * * * * */
	private Point3D _p; // In [Lat,Lon,Alt]
	private String _id;
	/* * * * * * * * * * * * * *  Constructor * * * * * * * * * * * * * * * */
	public Fruit(Point3D p, String id)
	{
		this._id = id;
		this._p = p;
	}
	/* * * * * * * * * * * * * *  toString * * * * * * * * * * * * * * * */
	public String toString()
	{
		String ans = "";
		ans += "Fruit id: " + _id + " Point: " + _p;
		return ans;
	}

	/* * * * * * * * * * * * * *  Setters and Getters * * * * * * * * * * * * * * * */
	public Point3D getP() {
		return _p;
	}
	public void setP(Point3D p) {
		this._p = p;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}



}
