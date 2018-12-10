package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import scene.SceneManager;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Platform.setImplicitExit(true);
	        primaryStage.setOnCloseRequest((ae) -> {
	            Platform.exit();
	            System.exit(0);
	        });
			
			SceneManager.initialize(primaryStage);
			SceneManager.gotoScene("Welcome");
			primaryStage.setResizable(false);
			primaryStage.setTitle("DORA Cafe");
			primaryStage.centerOnScreen();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
