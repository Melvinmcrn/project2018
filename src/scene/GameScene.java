package scene;

import gameScene.MainGame;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.VBox;
import logic.GameLogic;

public class GameScene extends VBox{
	
	private MainGame mainGame;
	private GameLogic gameLogic;
	
	public GameScene() {
		
		gameLogic = new GameLogic();
		mainGame = new MainGame();
		this.getChildren().addAll(mainGame);
		
		AnimationTimer animation = new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				//mainGame.paintComponent();
				gameLogic.logicUpdate();
				
			}
		};
		animation.start();
	}

}
