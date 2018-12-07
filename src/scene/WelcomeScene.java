package scene;

import button.TextButton;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class WelcomeScene extends StackPane {

	private String logo_path = ClassLoader.getSystemResource("images/Logo.png").toString();
	private Image logo = new Image(logo_path);
	private Media media = new Media(ClassLoader.getSystemResource("videos/WelcomeBG.mp4").toExternalForm());
	private MediaPlayer player = new MediaPlayer(media);
	
	public WelcomeScene() {
		
		player.setAutoPlay(true);
		MediaView view = new MediaView(player);
		player.setOnEndOfMedia(new Runnable() {
			@Override
			public void run() {
				player.seek(Duration.ZERO);
				player.play();
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

		buttonSet.getChildren().addAll(canvas,buttonBox);
		this.getChildren().addAll(view, buttonSet);
	}

}
