/**
 * This Class represent Pacman that implements GIS_Pacman
 * each Pacman has Pacman Data and Geom Element
 * @author Tzvi Mints and Or Abuhazira
 */
package Objects;
import java.awt.Color;
import java.util.Random;

import Coords.MyCoords;
import Geom.Geom_element;
import Geom.Point3D;

public class Pacman implements GIS_Pacman{
	/* * * * * * * * * * * * * *  Initialization Variables * * * * * * * * * * * * * * * */
	private Geom_element geo;
	private PacmanData PacmanData;

	/* * * * * * * * * * * * * * * * * * Setters and Getters * * * * * * * * * * * * * * * */
	public PacmanData getInfo() { return PacmanData; }
	/* * * * * * * * * * * * * * * * * * Constructors * * * * * * * * * * * * * * * */
	public Pacman(String id,String speed,String radius, Geom_element geo) { 
		// ************ initialize Geom_element ************ //
		this.geo = geo;
		// ************ initialize Pacman Data ************ //
		Random rand = new Random();
		float r = rand.nextFloat();
		float g = rand.nextFloat();
		float b = rand.nextFloat();
		Color randomColor = new Color(r, g, b);
		PacmanData = new PacmanData(id,speed,radius,randomColor);
	}
	public Pacman(Pacman p) {
		this.geo = p.geo;
		this.PacmanData = p.PacmanData;
	}

	/* * * * * * * * * * * * * * * * * * toString * * * * * * * * * * * * * * * */
	public String toString()
	{
		String ans = "Pacman:" + "--> ";
		ans += PacmanData.toString();
		ans +=  "," + "Geom element" + ":" + geo ;
		return ans;
	}
	/* * * * * * * * * * * * * * * * * * Override * * * * * * * * * * * * * * * */
	@Override
	public Point3D getGeom() {
		return (Point3D) geo;
	}

	@Override
	public Meta_data getData() {
		return PacmanData;
	}
	@Override
	public void translate(Point3D vec) {
		Point3D p = (Point3D)this.getGeom();
		MyCoords coords = new MyCoords();
		geo = new Point3D(coords.add(p, vec));
	}
}
