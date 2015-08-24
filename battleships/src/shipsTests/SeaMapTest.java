package shipsTests;

import static org.junit.Assert.*;

import org.junit.Test;

import ships.SeaMap;
import ships.Ship;

public class SeaMapTest {

	@Test
	public void testSeaMap() {
		SeaMap map = new SeaMap(1, 1);
	    assertEquals("map should have only one child", 1, map.getChildren().size());

		SeaMap map_2 = new SeaMap(5, 5);
	    assertEquals("map should have 25 children", 25, map_2.getChildren().size());
	}

	@Test
	public void testToggleShowShips() {
		SeaMap map = new SeaMap(1, 1);
		for(Ship s : map.getShips()) {
			assertEquals("ships should initially be invisible", false, s.getVisible().get());
		}
		map.toggleShowShips();
		for(Ship s : map.getShips()) {
			assertEquals("ships should be visible after toggleShowShips", true, s.getVisible().get());
		}
	}

	@Test
	public void testShipKilled() {
		SeaMap map = new SeaMap(1, 1);
		while(!map.getShips_alive().get())
		{
			map.generate(1, 1); //generate until one live ship is on the map
		}
	    assertEquals("initially ship should be alive", true, map.getShips_alive().get());
	    map.shipKilled();
	    assertEquals("ship should be dead now", false, map.getShips_alive().get());
	}

}
