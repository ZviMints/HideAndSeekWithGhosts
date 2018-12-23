/**
 * This Class represent Ghost that implements GIS_Ghost
 * each Ghost has Ghost Data and Geom Element
 * @author Tzvi Mints and Or Abuhazira
 */
package Box;
import Coords.MyCoords;
import Geom.Point3D;

public class Box implements GIS_Box{
	/* * * * * * * * * * * * * *  Initialization Variables * * * * * * * * * * * * * * * */
	private Point3D bottom_p;
	private Point3D upper_p;
	private BoxData BoxData;

	/* * * * * * * * * * * * * * * * * * Setters and Getters * * * * * * * * * * * * * * * */
	public BoxData getInfo() { return BoxData; }
	/* * * * * * * * * * * * * * * * * * Constructors * * * * * * * * * * * * * * * */
	public Box(String id, Point3D upper_p, Point3D bottom_p) { 
		// ************ initialize 3D Point ************ //
		this.bottom_p = bottom_p;
		this.upper_p = upper_p;
		// ************ initialize Box Data ************ //
		BoxData = new BoxData(id);
	}
	public Box(Box b) {
		this.upper_p = b.getUpper3DPoint();
		this.bottom_p = b.getBottom3DPoint();
		this.BoxData = b.BoxData;
	}

	/* * * * * * * * * * * * * * * * * * toString * * * * * * * * * * * * * * * */
	public String toString()
	{
		String ans = "Box:" + "--> ";
		ans += BoxData.toString();
		ans +=  "," + "Bottom Point3D" + ":" + this.bottom_p ;
		ans +=  "," + "Up Point3D" + ":" + this.upper_p ;
		return ans;
	}
	/* * * * * * * * * * * * * * * * * * Override * * * * * * * * * * * * * * * */

	@Override
	public Point3D getUpper3DPoint() {
		return this.upper_p;
	}
	@Override
	public Point3D getBottom3DPoint() {
		// TODO Auto-generated method stub
		return this.bottom_p;
	}
}
