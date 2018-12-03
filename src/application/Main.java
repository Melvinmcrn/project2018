package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import scene.SceneManager;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			SceneManager.initialize(primaryStage);
			SceneManager.gotoScene("WelcomeScene");
			primaryStage.setResizable(false);
			primaryStage.setTitle("Farm Ruk Cafe");
			primaryStage.centerOnScreen();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
