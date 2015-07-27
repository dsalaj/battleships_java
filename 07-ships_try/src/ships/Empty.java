package ships;

import javafx.scene.Group;
import javafx.scene.shape.Rectangle;

public class Empty extends Group {

	public Empty(SeaMap map) {
		Rectangle r = new Rectangle(20, 20);
		r.setFill(Constants.seaColor);
		this.getChildren().add(r);
        setOnMouseClicked(m -> {
        	map.click();

        });
	}

}
