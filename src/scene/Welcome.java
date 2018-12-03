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
	
	// private BaseButton playButton;
	String bg_path = ClassLoader.getSystemResource("images/WelcomeBackground.png").toString();
	private Image bg_image = new Image(bg_path);
	BackgroundImage bg = new BackgroundImage(bg_image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false));
	//private WritableImage bg_wimage = new WritableImage(bg_image.getPixelReader(), 0, 0, 800, 600);
	//private BackgroundImage bg = new BackgroundImage(bg_wimage, null, null, null, null);

	public Welcome() {
		
		this.setAlignment(Pos.CENTER);
		this.setBackground(new Background(bg));
		//Canvas canvas = new Canvas(300, 300);
		//GraphicsContext gc = canvas.getGraphicsContext2D();

		//VBox buttonBox = new VBox();
		// playButton

	}

}
