import javafx.animation.KeyFrame;
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

public class View extends Application {
	
	private static final Integer STARTTIME = 15;
	private Timeline timeline;
	private Label timerLabel = new Label();
	private Integer timeSeconds = STARTTIME;

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
		timerLabel.setText(timeSeconds.toString());
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

				timeSeconds = STARTTIME;

				//update timer label
				timerLabel.setText(timeSeconds.toString());
				timeline = new Timeline();
				timeline.setCycleCount(Timeline.INDEFINITE);
				timeline.getKeyFrames().add(
						new KeyFrame(Duration.seconds(1),
							new EventHandler<ActionEvent>() {
								
								//keyframe event handler
								@Override
								public void handle(ActionEvent event) {
									timeSeconds--;
									//update timerLabel
									timerLabel.setText(
										timeSeconds.toString());
									if(timeSeconds <= 0) {
										timeline.stop();
									}
								}
						}));
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
