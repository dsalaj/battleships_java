package ships;

import java.util.ArrayList;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Group;

public class SeaMap extends Group {

	public BooleanProperty ships_alive = new SimpleBooleanProperty(false);
	private int no_ships_alive;
	private int clicks;
	private ArrayList<Ship> ships = new ArrayList<Ship>();

	public SeaMap(int x, int y) {
		generate(x, y);
	}

	public void generate(int x, int y) {
		no_ships_alive = 0;
		clicks = 0;
		for(int row = 0; row < x; row++) {
			for(int col = 0; col < y; col++) {
				boolean decision = (Math.random() > 0.7) ? true : false;
				Group g = (decision ? new Ship(this) : new Empty(this));

				g.setLayoutX(row*22);
				g.setLayoutY(col*22);
				getChildren().add(g);
				if(decision)
				{
					ships_alive.set(true);
					no_ships_alive++;
					getShips().add((Ship)g);
				}
			}
		}
	}

	public void toggleShowShips() {
		for(Ship s : getShips()) {
			s.toggleColor();
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

	public ArrayList<Ship> getShips() {
		return ships;
	}

	public BooleanProperty getShips_alive() {
		return ships_alive;
	}
}
