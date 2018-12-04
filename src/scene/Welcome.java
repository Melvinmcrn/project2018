package scene;

import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;

public class Welcome extends VBox {
	
	private String bg_path = ClassLoader.getSystemResource("images/WelcomeBackground2.jpg").toString();
	private Image bg_image = new Image(bg_path);
	private String logo_path = ClassLoader.getSystemResource("images/Logo.png").toString();
	private Image logo = new Image(logo_path);
	private WritableImage bg_wimage = new WritableImage(bg_image.getPixelReader(), 0, 0, 800, 600);
	private BackgroundSize bgSize = new BackgroundSize(800, 600, true, true, true, true);
	private BackgroundImage bg = new BackgroundImage(bg_wimage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bgSize);
	
	public Welcome() {
		
		this.setAlignment(Pos.TOP_CENTER);
		this.setBackground(new Background(bg));
		this.setSpacing(65);
		
		Canvas canvas = new Canvas(350, 186);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.drawImage(logo, 0, 0);

		VBox buttonBox = new VBox();
		MyButton playButton = new MyButton("Play");
		MyButton highScoreButton = new MyButton("High Score");
		MyButton exitButton = new MyButton("Exit");
		buttonBox.setSpacing(25);
		buttonBox.getChildren().addAll(playButton, highScoreButton, exitButton);
		buttonBox.setAlignment(Pos.CENTER);

		this.getChildren().addAll(canvas,buttonBox);
	}

}
