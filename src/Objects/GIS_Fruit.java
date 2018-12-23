package Objects;

import Geom.Geom_element;
import Geom.Point3D;

public interface GIS_Fruit {
	public Geom_element getGeom();
	public void translate(Point3D vec);
}
