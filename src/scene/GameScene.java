package scene;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import logic.GameLogic;

public class GameScene extends Pane {

	private GameLogic gameLogic;

	private static Pane mainGame;
	private static Pane statusBar;
	private static VBox gamePane;
	private final Image gameBG = new Image(ClassLoader.getSystemResource("images/GameBG.png").toString());
	private final Image statusBarBG = new Image(
			ClassLoader.getSystemResource("images/StatusBar/StatusBarBG.jpg").toString());
	private final Font statusFont = Font
			.loadFont(ClassLoader.getSystemResourceAsStream("fonts/Otaku Rant italbold.ttf"), 50);
	private final Font statusMessageFont = Font.loadFont(ClassLoader.getSystemResourceAsStream("fonts/Otaku_Rant.ttf"),
			35);

	private static Media gameBGM = new Media(ClassLoader.getSystemResource("musics/GameBGM.mp3").toExternalForm());
	private static MediaPlayer gameMisc = new MediaPlayer(gameBGM);
	private static AnimationTimer animation;

	private static Label statusMessage;
	private Label clock;

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

		mainGame.getChildren().addAll(GameLogic.getFoodContainer());
		mainGame.getChildren().addAll(GameLogic.getEatBarContainer());
		mainGame.getChildren().addAll(GameLogic.getTableContainer());
		mainGame.getChildren().addAll(GameLogic.getcookBarContainer());

		// Set status bar
		statusBar = new Pane();
		statusBar.setPrefHeight(120);
		statusBar.setPrefWidth(800);
		statusBar.setLayoutX(0);
		statusBar.setLayoutY(480);
		statusBar.setBackground(new Background(new BackgroundImage(statusBarBG, null, null, null, null)));
		statusBar.setVisible(true);

		statusMessage = new Label();
		statusMessage.setLayoutX(170);
		statusMessage.setLayoutY(10);
		statusMessage.setMaxWidth(550);
		statusMessage.setFont(statusMessageFont);
		statusMessage.setTextFill(Color.FLORALWHITE);

		long endTime = System.currentTimeMillis() + 301000;
		this.clock = new Label("0:00");
		this.clock.setLayoutX(170);
		this.clock.setLayoutY(58);
		this.clock.setMaxWidth(170);
		this.clock.setTextFill(Color.BEIGE);
		this.clock.setFont(statusFont);

		Label money = new Label(Integer.toString(GameLogic.getMoney()));
		money.setLayoutX(420);
		money.setLayoutY(58);
		money.setFont(statusFont);

		Label score = new Label(Integer.toString(GameLogic.getScore()));
		score.setLayoutX(620);
		score.setLayoutY(58);
		score.setFont(statusFont);

		statusBar.getChildren().add(gameLogic.getPlayerImage());
		statusBar.getChildren().add(statusMessage);
		statusBar.getChildren().add(this.clock);
		statusBar.getChildren().add(money);
		statusBar.getChildren().add(score);

		// Add MainGame and StatusBar to root
		gamePane = new VBox();
		gamePane.getChildren().addAll(view, mainGame, statusBar);

		this.getChildren().add(gamePane);

		animation = new AnimationTimer() {

			@Override
			public void handle(long now) {
				long currentTime = System.currentTimeMillis();

				// set time to clock
				long passedTime = (endTime - currentTime) / 1000;
				setClock(passedTime);

				// set score and money
				score.setText(Integer.toString(GameLogic.getScore()));
				money.setText(Integer.toString(GameLogic.getMoney()));

				// set GAME OVER
				if (endTime - System.currentTimeMillis() <= 0 || GameLogic.getScore() <= 0) {
					animation.stop();
					GameLogic.endGame();
				}

				gameLogic.logicUpdate();
			}
		};
		animation.start();
	}

	private void setClock(long timePassed) {
		int minute = (int) (timePassed / 60);
		int second = (int) (timePassed % 60);
		String minuteString = Integer.toString(minute);
		String secondString = Integer.toString(second);
		if (second / 10 == 0) {
			secondString = "0" + secondString;
		}

		this.clock.setText(minuteString + ":" + secondString);

	}

	public static MediaPlayer getMiscPlayer() {
		return gameMisc;
	}

	public static Pane getMainGame() {
		return mainGame;
	}

	public static void setStatusMessage(String message) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				statusMessage.setText(message);
			}
		});
	}

}
