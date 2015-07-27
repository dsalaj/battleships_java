package ships;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;

public class Ship extends Group {

	BooleanProperty alive = new SimpleBooleanProperty(true);

	public Ship(SeaMap map) {
		Rectangle r = new Rectangle(20, 20);
		r.setFill(Constants.liveColor);
		this.getChildren().add(r);

        alive.addListener((e, o, n) -> {
            r.setFill(n ? Constants.liveColor : Constants.deadColor);
        });
        setOnMouseClicked(m -> {
        	map.click();
        	if(alive.get()) {
        		alive.set(false);
        		map.shipKilled();
        	}
        });
	}

}
