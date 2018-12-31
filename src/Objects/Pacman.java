/**
 * This Class represent Pacman 
 * @author Tzvi Mints and Or Abuhazira
 */
package Objects;
import java.awt.Image;
import java.awt.Toolkit;

import Geom.Point3D;

public class Pacman implements Element {
	/* * * * * * * * * * * * * *  Initialization Variables * * * * * * * * * * * * * * * */
	private Point3D _p; // In [Lat,Lon,Alt]
	private String _id;
	private double _speed;
	private double _radius;
	private Image PacmanImage = Toolkit.getDefaultToolkit().getImage("./img/Pacman.png"); // Pacman image
	private boolean Alive = true;

	/* * * * * * * * * * * * * *  Constructor * * * * * * * * * * * * * * * */
	public Pacman(Point3D p, String id, double speed, double radius)
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
		ans += "Pacman id: " + _id + " Point: " + _p + " Speed: " + _speed + " Radius: " + _radius;
		return ans;
	}
	/* * * * * * * * * * * * * *  Setters and Getters * * * * * * * * * * * * * * * */
	public Point3D getP() {
		return _p;
	}
	public void setP(double x, double y, double z) {
		this._p.setX(x);
		this._p.setY(y);
		this._p.setZ(z);
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
	public Image getPacmanImage() {
		return PacmanImage;
	}
	public void setPacmanImage(Image pacmanImage) {
		PacmanImage = pacmanImage;
	}
	public boolean isAlive() {
		return Alive;
	}
	public void setAlive(boolean Alive) {
		this.Alive = Alive;
	}	
}
