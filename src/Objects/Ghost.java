/**
 * This Class represent Ghost 
 * @author Tzvi Mints and Or Abuhazira
 */
package Objects;
import java.awt.Image;
import java.awt.Toolkit;

import Geom.Point3D;

public class Ghost implements Element {
	/* * * * * * * * * * * * * *  Initialization Variables * * * * * * * * * * * * * * * */
	private Point3D _p; // In [Lat,Lon,Alt]
	private String _id;
	private double _speed;
	private double _radius;
	private Image GhostImage = Toolkit.getDefaultToolkit().getImage("./img/Ghost.png"); //  Ghost image

	/* * * * * * * * * * * * * *  Constructor * * * * * * * * * * * * * * * */
	public Ghost(Point3D p, String id, double speed, double radius)
	{
		this._id = id;
		this._speed = speed;
		this._radius = radius;
		this._p = p;
	}
	/* * * * * * * * * * * * * *  toString * * * * * * * * * * * * * * * */
	public String toString()
	{
		String ans = "";
		ans += "Ghost id: " + _id + " Point: " + _p + " Speed: " + _speed + " Radius: " + _radius;
		return ans;
	}
	/* * * * * * * * * * * * * *  Setters and Getters * * * * * * * * * * * * * * * */
	public Point3D getP() {
		return _p;
	}
	public void setP(Point3D _p) {
		this._p = _p;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public double get_speed() {
		return _speed;
	}
	public void set_speed(double _speed) {
		this._speed = _speed;
	}
	public double get_radius() {
		return _radius;
	}
	public void set_radius(double _radius) {
		this._radius = _radius;
	}
	public Image getGhostImage() {
		return GhostImage;
	}
	public void setGhostImage(Image ghostImage) {
		GhostImage = ghostImage;
	}
	public void setP(double x, double y, double z) {
		this._p.setX(x);
		this._p.setY(y);
		this._p.setZ(z);
	}
}
