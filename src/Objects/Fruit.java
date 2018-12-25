/**
 * This Class represent Fruit 
 * @author Tzvi Mints and Or Abuhazira
 */
package Objects;
import java.awt.Image;
import java.awt.Toolkit;

import Geom.Point3D;

public class Fruit {
	private Image FruitImage = Toolkit.getDefaultToolkit().getImage("./img/Fruit.png"); // Fruit image
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
	public Image getFruitImage() {
		return FruitImage;
	}
	public void setFruitImage(Image fruitImage) {
		FruitImage = fruitImage;
	}
}
