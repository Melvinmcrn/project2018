package application;

import javafx.application.Application;
import javafx.stage.Stage;
import scene.SceneManager;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			SceneManager.initialize(primaryStage);
			SceneManager.gotoScene("Welcome");
			primaryStage.setResizable(false);
			primaryStage.setTitle("DORA CAFE");
			primaryStage.centerOnScreen();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
