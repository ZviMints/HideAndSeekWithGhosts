package Testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Geom.Point3D;
import Objects.Pacman;

class PacmanTest {
	private Point3D p = new Point3D(32.105848,35.202429); // point of Pacman1
	private Pacman pac;
	

	@Test
	void testPacman() {
		pac = new Pacman(p, "1", 10, 1); // initialization Pacman
		Point3D p_exp = new Point3D(32,35); // point of Pacman2
		Pacman exp = new Pacman(p_exp, "1", 10, 1);// initialization Pacman2
		assertNotEquals(exp.toString(), pac.toString()); // if pacman 1 and 2 not equals
	}
	@Test
	void testToString() {
		pac = new Pacman(p, "1", 10, 1); // initialization Pacman
		String exp = "Pacman id: " + 1 + " Point: " + p 
				+ " Speed: " + 10.0 + " Radius: " + 1.0; // How printing should be
		assertEquals(exp , pac.toString());
		
	}
	@Test
	void testGetP() {
		pac = new Pacman(p, "1", 10, 1);  // initialization Pacmam
		assertEquals(p.toString(), pac.getP().toString());
	}

	@Test
	void testSetP() {
		Point3D p_set = new Point3D(32,35); // New point for Pacman
		pac = new Pacman(p, "1", 10, 1); // initialization Pacmam
		pac.setP(p_set.getX(), p_set.getY(), 0); // Set new point for Pacman
		assertEquals(p_set.toString(),pac.getP().toString());
	}
	@Test
	void testGet_id() {
		pac = new Pacman(p, "1", 10, 1);  // initialization Pacmam
		assertEquals("1", pac.get_id());
	}
	@Test
	void testSet_id() {
		pac = new Pacman(p, "1", 10, 1);  // initialization Pacmam
		pac.set_id("2"); // Set new id for Pacman
		assertEquals("2", pac.get_id());
	}
	@Test
	void testGet_speed() {
		pac = new Pacman(p, "1", 10, 1);  // initialization Pacmam
		assertEquals(10, pac.get_speed());
	}
	@Test
	void testSet_speed() {
		pac = new Pacman(p, "1", 10, 1);  // initialization Pacmam
		pac.set_speed(12.2); // Set new speed for Pacman
		assertEquals(12.2, pac.get_speed());
	}

	@Test
	void testGet_radius() {
		pac = new Pacman(p, "1", 10, 1);  // initialization Pacmam
		assertEquals(1, pac.get_radius());
	}

	@Test
	void testSet_radius() {
		pac = new Pacman(p, "1", 10, 1);  // initialization Pacmam
		pac.set_radius(1.25);; // Set new radius for Pacman
		assertEquals(1.25, pac.get_radius());;
	}

}
