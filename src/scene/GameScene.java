package scene;

import gameScene.MainGame;
import javafx.scene.layout.VBox;

public class GameScene extends VBox{
	
	private MainGame mainGame = new MainGame();
	
	public GameScene() {
		this.getChildren().add(mainGame);
	}

}
