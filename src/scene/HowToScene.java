package scene;

import button.NavigationButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class HowToScene extends StackPane {
	
	private static Media howtoBG = new Media(ClassLoader.getSystemResource("videos/HowToBG.mp4").toExternalForm());
	private static MediaPlayer howtoPlayer = new MediaPlayer(howtoBG);
	private ImageView howtoPic;
	private int position;
	
	HowToScene() {
		howtoPlayer.setAutoPlay(true);
		MediaView vidView = new MediaView(howtoPlayer);
		howtoPlayer.setOnEndOfMedia(new Runnable() {
			@Override
			public void run() {
				howtoPlayer.seek(Duration.ZERO);
				howtoPlayer.play();
			}
		});
		
		howtoPic = new ImageView(new Image(ClassLoader.getSystemResource("howto/HowtoPic1.png").toString()));
		position = 1;
		HBox navigationBtnBox = new HBox();
		NavigationButton nextButton = new NavigationButton("Next", "") {
			@Override
			protected void setEvent() {
				this.setOnMouseClicked((MouseEvent event) -> {
					position++;
					mouseClicked.play();
					if(position < 10) {
						howtoPic.setImage(new Image(ClassLoader.getSystemResource("howto/HowtoPic" + position + ".png").toString()));
						System.out.println(name);
					}
					else {
						position = 0;
						SceneManager.gotoScene("Welcome");
						System.out.println("Welcome");
					}
				});
				this.setOnMouseEntered((MouseEvent event) -> {
					mouseIn.play();
					drawButtonGlow();
				});
				this.setOnMouseExited((MouseEvent event) -> {
					drawButton();
				});
			}
		};
		NavigationButton backButton = new NavigationButton("Back", "") {
			@Override
			protected void setEvent() {
				this.setOnMouseClicked((MouseEvent event) -> {
					position--;
					mouseClicked.play();
					if(position > 0) {
						howtoPic.setImage(new Image(ClassLoader.getSystemResource("howto/HowtoPic" + position + ".png").toString()));
						System.out.println(name);
					}
					else {
						position = 0;
						SceneManager.gotoScene("Welcome");
						System.out.println("Welcome");
					}
				});
				this.setOnMouseEntered((MouseEvent event) -> {
					mouseIn.play();
					drawButtonGlow();
				});
				this.setOnMouseExited((MouseEvent event) -> {
					drawButton();
				});
			}
		};
		NavigationButton homeButton = new NavigationButton("Home", "Welcome");
		navigationBtnBox.setAlignment(Pos.BOTTOM_LEFT);
		navigationBtnBox.setPadding(new Insets(0, 30, 20, 30));
		navigationBtnBox.setSpacing(300);
		navigationBtnBox.getChildren().addAll(backButton, homeButton, nextButton);
		
		this.getChildren().addAll(vidView, howtoPic, navigationBtnBox);
	}
}
