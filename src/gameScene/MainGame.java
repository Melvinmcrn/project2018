package gameScene;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class MainGame extends Canvas {
	
	private GraphicsContext gc;

	public MainGame() {
		super(800, 400);
		this.setVisible(true);
	}
	
	public void paintComponent() {
		gc = this.getGraphicsContext2D();
		
	}
}
