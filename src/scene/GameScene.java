package scene;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
import logic.GameLogic;

public class GameScene extends VBox {

	private GameLogic gameLogic;

	private Pane mainGame;
	private Image gameBG = new Image(ClassLoader.getSystemResource("images/GameBackground.png").toString());
	private static Media gameBGM = new Media(ClassLoader.getSystemResource("musics/GameBGM.mp3").toExternalForm());
	private static MediaPlayer gameMisc = new MediaPlayer(gameBGM);

	public GameScene() {

		gameMisc.setAutoPlay(true);
		MediaView view = new MediaView(gameMisc);
		gameMisc.setOnEndOfMedia(new Runnable() {
			@Override
			public void run() {
				gameMisc.seek(Duration.ZERO);
				gameMisc.play();
			}
		});

		gameLogic = new GameLogic();

		// Set Main Game
		mainGame = new Pane();
		mainGame.setPrefHeight(480);
		mainGame.setPrefWidth(800);
		mainGame.setBackground(new Background(new BackgroundImage(gameBG, null, null, null, null)));
		mainGame.setVisible(true);

		mainGame.getChildren().addAll(GameLogic.getTableContainer());
		mainGame.getChildren().addAll(GameLogic.getFoodContainer());

		this.getChildren().addAll(view, mainGame);

		AnimationTimer animation = new AnimationTimer() {

			@Override
			public void handle(long now) {
				// mainGame.paintComponent();
				gameLogic.logicUpdate();

			}
		};
		animation.start();
	}

	public static MediaPlayer getMiscPlayer() {
		return gameMisc;
	}

}
