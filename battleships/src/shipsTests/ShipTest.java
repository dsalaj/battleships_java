package shipsTests;

import static org.junit.Assert.*;

import org.junit.Test;

import ships.SeaMap;
import ships.Ship;

public class ShipTest {

	@Test
	public void testToggleColor() {

		SeaMap map = new SeaMap(1, 1);
	    // MyClass is tested
	    Ship tester = new Ship(map);

	    // assert statements
	    assertEquals("visible must be false after initialization", false, tester.getVisible().get());
	    tester.toggleColor();
	    assertEquals("visible must be true after first toggle", true, tester.getVisible().get());

	}

}
