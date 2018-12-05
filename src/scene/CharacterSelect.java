package scene;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class CharacterSelect extends StackPane {

	private Image logo = new Image(ClassLoader.getSystemResource("images/CharSelectLogo.png").toString());;
	private GraphicsContext gc;

	public CharacterSelect() {

		// Video Backgorund
		Media media = new Media(ClassLoader.getSystemResource("videos/CharacterSelectBackground.mp4").toExternalForm());
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

		// Top Logo
		Canvas logoCanvas = new Canvas(245, 130);
		gc = logoCanvas.getGraphicsContext2D();
		gc.drawImage(logo, 0, 0);

		// Character Select

		// Navigation Button
		NavigationButton nextButton = new NavigationButton("Next");
		NavigationButton backButton = new NavigationButton("Back");

		HBox navigationBtnBox = new HBox();
		navigationBtnBox.getChildren().addAll(backButton, nextButton);
		navigationBtnBox.setAlignment(Pos.CENTER_LEFT);
		navigationBtnBox.setPadding(new Insets(20, 30, 20, 30));
		navigationBtnBox.setSpacing(650);

		// BorderPane
		BorderPane borderPane = new BorderPane();

		borderPane.setTop(logoCanvas);
		BorderPane.setAlignment(logoCanvas, Pos.TOP_CENTER);

		borderPane.setBottom(navigationBtnBox);
		BorderPane.setAlignment(navigationBtnBox, Pos.BOTTOM_CENTER);

		this.getChildren().addAll(view, borderPane);
	}
}