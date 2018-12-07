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
import javafx.scene.layout.VBox;

public class WelcomeScene extends VBox {
	
	private String bg_path = ClassLoader.getSystemResource("images/WelcomeBackground.jpg").toString();
	private Image bg_image = new Image(bg_path);
	private String logo_path = ClassLoader.getSystemResource("images/Logo.png").toString();
	private Image logo = new Image(logo_path);
	private BackgroundSize bgSize = new BackgroundSize(800, 600, true, true, true, true);
	private BackgroundImage bg = new BackgroundImage(bg_image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bgSize);
	
	public WelcomeScene() {
		
		this.setAlignment(Pos.TOP_CENTER);
		this.setBackground(new Background(bg));
		this.setSpacing(65);
		
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

		this.getChildren().addAll(canvas,buttonBox);
	}

}
