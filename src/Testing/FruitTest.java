package Testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Geom.Point3D;
import Objects.Fruit;

class FruitTest {
	private Point3D p = new Point3D(32.105848,35.202429); // point of Fruit 1
	private Fruit f;

	@Test
	void testFruit() {
		f = new Fruit(p, "1"); // initialization Fruit 1
		Point3D p_exp = new Point3D(32,35); // point of Fruit 2
		Fruit exp = new Fruit(p_exp, "1");// initialization Fruit 2
		assertNotEquals(exp.toString(), f.toString()); // if Fruit 1 and 2 not equals
	}

	@Test
	void testToString() {
		f = new Fruit(p, "1"); // initialization Fruit
		String exp = "Fruit id: " + 1 + " Point: " + p ; // How printing should be
		assertEquals(exp , f.toString());
	}

	@Test
	void testGetP() {
		f = new Fruit(p, "1");  // initialization Fruit
		assertEquals(p.toString(), f.getP().toString());
	}

	@Test
	void testSetP() {
		Point3D p_set = new Point3D(32,35); // New point for Fruit
		f = new Fruit(p, "1"); // initialization Fruit
		f.setP(p_set.getX(), p_set.getY(), 0); // Set new point for Fruit
		assertEquals(p_set.toString() ,f.getP().toString());
	}
	@Test
	void testGet_id() {
		f = new Fruit(p, "1");  // initialization Fruit
		assertEquals("1", f.get_id());
	}

	@Test
	void testSet_id() {
		p = new Point3D(32.105848,35.202429); // point of Fruit
		f = new Fruit(p, "1");  // initialization Fruit
		f.set_id("2");
		assertEquals("2", f.get_id());
	}

}
