/**
 * This Class represent Fruit that implements GIS_Fruit
 * each Fruit has Fruit Data and Geom Element
 * @author Tzvi Mints and Or Abuhazira
 */
package Fruit;
import Coords.MyCoords;
import Geom.Point3D;

public class Fruit implements GIS_Fruit{
	/* * * * * * * * * * * * * *  Initialization Variables * * * * * * * * * * * * * * * */
	private Point3D point;
	private FruitData FruitData;

	/* * * * * * * * * * * * * * * * * * Setters and Getters * * * * * * * * * * * * * * * */
	public FruitData getInfo() { return FruitData; }
	
	/* * * * * * * * * * * * * * * * * * Constructors * * * * * * * * * * * * * * * */
	public Fruit(String id, Point3D point) { 
		// ************ initialize Geom_element ************ //
		this.point = point;
		// ************ initialize Fruit Data ************ //
		FruitData = new FruitData(id);
	}
	public Fruit(Fruit f) {
		this.point = f.get3DPoint();
		this.FruitData = f.FruitData;
	}

	/* * * * * * * * * * * * * * * * * * toString * * * * * * * * * * * * * * * */
	public String toString()
	{
		String ans = "Fruit:" + "--> ";
		ans += FruitData.toString();
		ans +=  "," + "Point3D" + ":" + this.point;
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
