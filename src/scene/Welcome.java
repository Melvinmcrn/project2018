package scene;

import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.VBox;

public class Welcome extends VBox {

	// private BaseButton playButton;
	private Image bg_image = new Image(ClassLoader.getSystemResource("WelcomeBackground.png").toString());
	private WritableImage bg_wimage = new WritableImage(bg_image.getPixelReader(), 0, 0, 1024, 768);
	private BackgroundImage bg = new BackgroundImage(bg_wimage, null, null, null, null);

	public Welcome() {

		this.setAlignment(Pos.CENTER);
		this.setBackground(new Background(bg));
		Canvas canvas = new Canvas(300, 300);
		GraphicsContext gc = canvas.getGraphicsContext2D();

		VBox buttonBox = new VBox();
		// playButton

	}

}
