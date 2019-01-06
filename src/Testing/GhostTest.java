package Testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Geom.Point3D;
import Objects.Ghost;

class GhostTest {
	private Point3D p = new Point3D(32.105848,35.202429); ;
	private Ghost g;

	@Test
	void testGhost() {
		g = new Ghost(p, "1", 10, 1); // initialization Ghost1
		Point3D p_exp = new Point3D(32,35); // point of Ghost2
		Ghost exp = new Ghost(p_exp, "1", 10, 1);// initialization Ghost2
		assertNotEquals(exp.toString(), g.toString()); // if Ghost 1 and 2 not equals
	}

	@Test
	void testToString() {
		g = new Ghost(p, "1", 10, 1); // initialization Ghost
		String exp = "Ghost id: " + 1 + " Point: " + p 
				+ " Speed: " + 10.0 + " Radius: " + 1.0; // How printing should be
		assertEquals(exp , g.toString());
	}
	@Test
	void testGetP() {
		g = new Ghost(p, "1", 10, 1);  // initialization Ghost
		assertEquals(p.toString(), g.getP().toString());
	}
	@Test
	void testSetP() {
		Point3D p_set = new Point3D(32,35); // New point for Ghost
		g = new Ghost(p, "1", 10, 1); // initialization Ghost
		g.setP(p_set); // Set new point for Ghost
		assertEquals( p_set.toString(),g.getP().toString());
	}

	@Test
	void testGet_id() {
		g = new Ghost(p, "1", 10, 1);  // initialization Ghost
		assertEquals("1", g.get_id());
	}

	@Test
	void testSet_id() {
		g = new Ghost(p, "1", 10, 1);  // initialization Ghost
		g.set_id("2"); // Set new id for Ghost
		assertEquals("2", g.get_id());
	}

	@Test
	void testGet_speed() {
		g = new Ghost(p, "1", 10, 1);  // initialization Ghost
		assertEquals(10, g.get_speed());
	}

	@Test
	void testSet_speed() {
		g = new Ghost(p, "1", 10, 1);  // initialization Ghost
		g.set_speed(15.43); // Set new speed for Ghost
		assertEquals(15.43, g.get_speed());
	}

	@Test
	void testGet_radius() {
		g = new Ghost(p, "1", 10, 1);  // initialization Ghost
		assertEquals(1, g.get_radius());
	}

	@Test
	void testSet_radius() {
		g = new Ghost(p, "1", 10, 1);  // initialization Ghost
		g.set_radius(2); // Set new speed for Ghost
		assertEquals(2, g.get_radius());
	}

}
