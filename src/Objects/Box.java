/**
 * This Class represent Box 
 * @author Tzvi Mints and Or Abuhazira
 */
package Objects;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

import Geom.Point3D;
public class Box {
	private static final long serialVersionUID = 1L;
	/* * * * * * * * * * * * * *  Initialization Variables * * * * * * * * * * * * * * * */
	private String id;
	private Point3D p0;
	private Point3D p1;
	private Image BoxImage = Toolkit.getDefaultToolkit().getImage("./img/Box.png");

	/* * * * * * * * * * * * * * * * * * Constructors * * * * * * * * * * * * * * * */
	public Box(String id, Point3D p0, Point3D p1) { 
	
		this.id = id;
		this.p0=p0;
		this.p1=p1;
	}
	/* * * * * * * * * * * * * * * * * * toString * * * * * * * * * * * * * * * */
	public String toString()
	{
		String ans = "Box:" + "--> ";
		ans += "ID" + ":" + this.id;
		ans +=  "," + "p0" + ":" + p0.toString();
		ans +=  "," + "p1" + ":" + p1.toString();
		return ans;
	}
	/* * * * * * * * * * * * * * * * * * Getter and Setters * * * * * * * * * * * * * * * */
	public String getId() { return id; }
	public Point3D getP0() { return p0; }
	public Point3D getP1() { return p1; }
	public Image getBoxImage() {
		return BoxImage;
	}
	public void setBoxImage(Image boxImage) {
		BoxImage = boxImage;
	}	
}
