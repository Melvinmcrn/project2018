package scene;

import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class CharacterSelect extends StackPane {

	private Image logo = new Image(ClassLoader.getSystemResource("images/CharSelectLogo.png").toString());;
	private GraphicsContext gc;

	public CharacterSelect() {
		
		//	Video Backgorund
		Media media = new Media(
				ClassLoader.getSystemResource("videos/CharacterSelectBackground.mp4").toExternalForm());
		MediaPlayer player = new MediaPlayer(media);
		player.setAutoPlay(true);
		MediaView view = new MediaView(player);
		player.setOnEndOfMedia(new Runnable() {
			@Override
			public void run() {
				player.seek(Duration.ZERO);
				player.play();
			}
		});
		
		//	Character Select
		BorderPane borderPane = new BorderPane();
		Canvas logoCanvas = new Canvas(245, 130);
		gc = logoCanvas.getGraphicsContext2D();
		gc.drawImage(logo, 0, 0);

		borderPane.setTop(logoCanvas);
		borderPane.setAlignment(logoCanvas, Pos.TOP_CENTER);

		this.getChildren().addAll(view, borderPane);
	}
}