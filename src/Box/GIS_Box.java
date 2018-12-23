package Box;
import Geom.Point3D;

/**
 * This interface represents a GIS element with geometric representation and meta data such as:
 * Orientation, color, string, timing...
 * @author Boaz Ben-Moshe
 *
 */
public interface GIS_Box {
	public Point3D getUpper3DPoint();
	public Point3D getBottom3DPoint();
}
