import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class View extends Application {
	
	private static final Integer STARTTIME = 15;
	private Timeline timeline;
	private Label timerLabel = new Label();
	private IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) {

		// Stup stage and scene (scene graph)
		primaryStage.setTitle("Hubris");
		Group root = new Group();
		Scene scene = new Scene(root, 300, 250);

		// Configure the Label
		//timerLabel.setText(timeSeconds.toString());
		timerLabel.textProperty().bind(timeSeconds.asString()); // bind timeseconds to display in label
		timerLabel.setTextFill(Color.RED);
		timerLabel.setStyle("-fx-font-size: 4em;");

		// Create and configure the button
		Button button = new Button();
		button.setText("Start Timer");
		button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				if(timeline != null) {
					timeline.stop();
				}

				timeSeconds.set(STARTTIME);
				timeline = new Timeline();
				timeline.getKeyFrames().add(
						new KeyFrame(Duration.seconds(STARTTIME + 1),
						new KeyValue(timeSeconds, 0)));	
				timeline.playFromStart();
			}
		});
		

		//create and config VBox
		VBox vb = new VBox(20);
		vb.setAlignment(Pos.CENTER);
		vb.setPrefWidth(scene.getWidth());
		vb.setLayoutY(30);
		vb.getChildren().addAll(button, timerLabel);
		root.getChildren().add(vb);

		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
