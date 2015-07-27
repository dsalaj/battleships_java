package ships;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Group;

public class SeaMap extends Group {

	public BooleanProperty ships_alive = new SimpleBooleanProperty(true);
	private int no_ships_alive;
	private int clicks;

	public SeaMap(int x, int y) {
		generate(x, y);
	}

	public void generate(int x, int y) {
		no_ships_alive = 0;
		clicks = 0;
		ships_alive.set(true);
		for(int row = 0; row < x; row++) {
			for(int col = 0; col < y; col++) {
				boolean decision = (Math.random() > 0.7) ? true : false;
				Group g = decision ? new Ship(this) : new Empty(this);
				g.setLayoutX(row*22);
				g.setLayoutY(col*22);
				getChildren().add(g);
				if(decision) no_ships_alive++;
			}
		}
	}

	public void shipKilled() {
		no_ships_alive--;
		if(no_ships_alive == 0) ships_alive.set(false);
	}

	public void click() {
		clicks++;
	}

	public int getClicks() {
		return clicks;
	}

}
