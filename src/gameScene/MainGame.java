package gameScene;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class MainGame extends Canvas {
	
	private GraphicsContext gc;
	
	public MainGame() {
		super(800, 480);
		this.setVisible(true);
		this.paintComponent();
	}
	
	public void paintComponent() {
		gc = this.getGraphicsContext2D();
		for(IRenderable entity : RenderableHolder.getInstance().getEntities()) {
			entity.draw(gc);
		}
	}
}
