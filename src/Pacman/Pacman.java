/**
 * This Class represent Pacman that implements GIS_Pacman
 * each Pacman has Pacman Data and Geom Element
 * @author Tzvi Mints and Or Abuhazira
 */
package Pacman;
import java.awt.Color;
import java.util.Random;
import Coords.MyCoords;
import Geom.Point3D;

public class Pacman implements GIS_Pacman{
	/* * * * * * * * * * * * * *  Initialization Variables * * * * * * * * * * * * * * * */
	private Point3D point;
	private PacmanData PacmanData;

	/* * * * * * * * * * * * * * * * * * Setters and Getters * * * * * * * * * * * * * * * */
	public PacmanData getInfo() { return PacmanData; }
	/* * * * * * * * * * * * * * * * * * Constructors * * * * * * * * * * * * * * * */
	public Pacman(String id,String speed,String radius, Point3D point) { 
		// ************ initialize 3D Point ************ //
		this.point = point;
		// ************ initialize Pacman Data ************ //
		PacmanData = new PacmanData(id,speed,radius);
	}
	public Pacman(Pacman p) {
		this.point = p.get3DPoint();
		this.PacmanData = p.PacmanData;
	}

	/* * * * * * * * * * * * * * * * * * toString * * * * * * * * * * * * * * * */
	public String toString()
	{
		String ans = "Pacman:" + "--> ";
		ans += PacmanData.toString();
		ans +=  "," + "Point3D" + ":" + this.point ;
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
