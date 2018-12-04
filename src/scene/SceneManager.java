package scene;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {

	private static Stage primaryStage;
	private static final int SCENE_WIDTH = 800;
	private static final int SCENE_HEIGHT = 600;
	private static Welcome welcome = new Welcome();
	private static HighScore highScore = new HighScore();

	private static Scene welcomeScene = new Scene(welcome, SCENE_WIDTH, SCENE_HEIGHT);
	private static Scene highScoreScene = new Scene(highScore, SCENE_WIDTH, SCENE_HEIGHT);

	public static void initialize(Stage stage) {
		primaryStage = stage;
		primaryStage.show();
	}

	public static void gotoScene(String scene) {

		if (scene.equals("Welcome")) {
			primaryStage.setScene(welcomeScene);
			primaryStage.show();
		} else if (scene.equals("Play")) {
			primaryStage.setScene(CharactorSelect);
			primaryStage.show();
		} else if (scene.equals("High Score")) {
			primaryStage.setScene(highScoreScene);
			primaryStage.show();

		} else if (scene.equals("Exit")) {
			primaryStage.close();
		}
	}
}
