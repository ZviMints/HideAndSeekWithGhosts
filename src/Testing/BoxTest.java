package Testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Geom.Point3D;
import Objects.Box;

class BoxTest {
	private Box box;
	private Point3D p0 = new Point3D(32.10214939,35.20281755); // point1 of Box 1
	private Point3D p1 = new Point3D(32.10541403,35.2033025); // point2 of Box 1
	

	@Test
	void testBox() {
		box = new Box("1", p0, p1); // initialization Box 1
		Point3D p_exp = new Point3D(32,35); // point1 of Box 2
		Box exp = new Box("1",p_exp ,p1);// initialization Box 2
		assertNotEquals(exp.toString(), box.toString()); // if Box 1 and 2 not equals
	}
	@Test
	void testToString() {
		box = new Box("1",p0,p1); // initialization Box
		String exp =  "Box:" + "--> ";
		exp += "ID" + ":" + "1";
		exp +=  "," + "p0" + ":" + p0.toString();
		exp +=  "," + "p1" + ":" + p1.toString();// How printing should be
		assertEquals(exp , box.toString());
	}

	@Test
	void testGetId() {
		box = new Box("1",p0,p1);  // initialization Box
		assertEquals("1", box.getId());
	}

	@Test
	void testGetP0() {
		box = new Box("1",p0,p1);  // initialization Box
		assertEquals(p0.toString(), box.getP0().toString());
	}

	@Test
	void testGetP1() {
		box = new Box("1",p0,p1);  // initialization Box
		assertEquals(p1.toString(), box.getP1().toString());
	}

}
