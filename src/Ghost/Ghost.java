/**
 * This Class represent Ghost that implements GIS_Ghost
 * each Ghost has Ghost Data and Geom Element
 * @author Tzvi Mints and Or Abuhazira
 */
package Ghost;
import Coords.MyCoords;
import Geom.Point3D;

public class Ghost implements GIS_Ghost{
	/* * * * * * * * * * * * * *  Initialization Variables * * * * * * * * * * * * * * * */
	private Point3D point;
	private GhostData GhostData;

	/* * * * * * * * * * * * * * * * * * Setters and Getters * * * * * * * * * * * * * * * */
	public GhostData getInfo() { return GhostData; }
	/* * * * * * * * * * * * * * * * * * Constructors * * * * * * * * * * * * * * * */
	public Ghost(String id,String speed,String radius, Point3D point) { 
		// ************ initialize 3D Point ************ //
		this.point = point;
		// ************ initialize Ghost Data ************ //
		GhostData = new GhostData(id,speed,radius);
	}
	public Ghost(Ghost g) {
		this.point = g.get3DPoint();
		this.GhostData = g.GhostData;
	}

	/* * * * * * * * * * * * * * * * * * toString * * * * * * * * * * * * * * * */
	public String toString()
	{
		String ans = "Ghost:" + "--> ";
		ans += GhostData.toString();
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
