package scene;

import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;

public class WelcomeScene extends VBox {
	
	public WelcomeScene() {
		
		this.setAlignment(Pos.CENTER);
		//this.setBackground(new Background(arg0));
		Canvas canvas = new Canvas();
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		VBox buttonBox = new VBox();
		
	}
	
}
