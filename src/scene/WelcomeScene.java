package scene;

import button.TextButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class WelcomeScene extends StackPane {

	private String logo_path = ClassLoader.getSystemResource("images/Logo.png").toString();
	private Image logo = new Image(logo_path);
	private static Media welcomeBG = new Media(ClassLoader.getSystemResource("videos/WelcomeBG.mp4").toExternalForm());
	private static Media mainBGM = new Media(ClassLoader.getSystemResource("musics/MainBGM.mp3").toExternalForm());
	private static MediaPlayer welcomePlayer = new MediaPlayer(welcomeBG);
	private static MediaPlayer mainMisc = new MediaPlayer(mainBGM);

	public WelcomeScene() {

		// Video Background + BGM

		mainMisc.setAutoPlay(true);
		MediaView miscView = new MediaView(mainMisc);
		mainMisc.setOnEndOfMedia(new Runnable() {
			@Override
			public void run() {
				mainMisc.seek(Duration.ZERO);
				mainMisc.play();
			}
		});

		welcomePlayer.setAutoPlay(true);
		MediaView vidView = new MediaView(welcomePlayer);
		welcomePlayer.setOnEndOfMedia(new Runnable() {
			@Override
			public void run() {
				welcomePlayer.seek(Duration.ZERO);
				welcomePlayer.play();
			}
		});

		VBox buttonSet = new VBox();

		buttonSet.setAlignment(Pos.TOP_CENTER);
		buttonSet.setSpacing(65);

		Canvas canvas = new Canvas(350, 186);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.drawImage(logo, 0, 0);

		VBox buttonBox = new VBox();
		TextButton playButton = new TextButton("Play");
		TextButton highScoreButton = new TextButton("Hall of Fame");
		TextButton exitButton = new TextButton("Exit");
		buttonBox.setSpacing(25);
		buttonBox.getChildren().addAll(playButton, highScoreButton, exitButton);
		buttonBox.setAlignment(Pos.CENTER);

		buttonSet.getChildren().addAll(canvas, buttonBox);

		this.getChildren().addAll(vidView, miscView, buttonSet);

	}

	public static MediaPlayer getVidPlayer() {
		return welcomePlayer;
	}

	public static MediaPlayer getMiscPlayer() {
		return mainMisc;
	}

}