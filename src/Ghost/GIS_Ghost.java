package Ghost;
import Geom.Point3D;

/**
 * This interface represents a GIS element with geometric representation and meta data such as:
 * Orientation, color, string, timing...
 * @author Boaz Ben-Moshe
 *
 */
public interface GIS_Ghost {
	public Point3D get3DPoint();
	public void translate(Point3D vec);
}
