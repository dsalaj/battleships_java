package ships;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			SeaMap map = new SeaMap(4, 5);
            map.ships_alive.addListener((e, o, n) -> {
            	if(!n) {
                	Alert alert = new Alert(AlertType.INFORMATION);
                	alert.setTitle("GAME OVER");
                	alert.setHeaderText("You completed the game in " + map.getClicks() + " moves!");
                	alert.showAndWait();
            	}
            });

            Button button = new Button("Generate map");
            button.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent e) {
	            	map.generate(4, 5);
	            }
	        });

            root.setBottom(button);
			root.setCenter(map);
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
