package shipsTests;

import static org.junit.Assert.*;

import org.junit.Test;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import ships.Empty;
import ships.SeaMap;

public class EmptyTest {

	@Test
	public void testEmpty() {
		SeaMap map = new SeaMap(1,1);
		Empty field = new Empty(map);
		assertEquals("Number of clicks should be 0", 0, map.getClicks());
		MouseEvent mouseEvent = new MouseEvent(MouseEvent.MOUSE_CLICKED, 1, 2, 3, 4, MouseButton.PRIMARY, 5, true, true, true, true, true, true, true, true, true, true, null);
	    field.fireEvent(mouseEvent);
		assertEquals("Number of clicks should be 1", 1, map.getClicks());
	}

}
