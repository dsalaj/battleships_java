package ships;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;

public class Ship extends Group {

	BooleanProperty alive = new SimpleBooleanProperty(true);
	BooleanProperty visible = new SimpleBooleanProperty(false);
	Rectangle r;

	public Ship(SeaMap map) {
		r = new Rectangle(20, 20);
		r.setFill(Constants.seaColor);
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

	public void toggleColor() {
		r.setFill(visible.get()? Constants.seaColor : Constants.liveColor);
		visible.set(!visible.get());
	}

}
