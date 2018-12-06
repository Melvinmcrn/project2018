package gameScene;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import logic.GameLogic;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class MainGame extends Canvas {
	
	private GraphicsContext gc;
	private GameLogic gameLogic;
	
	public MainGame() {
		super(800, 400);
		this.setVisible(true);
		gameLogic = new GameLogic();
		this.paintComponent();
	}
	
	public void paintComponent() {
		gc = this.getGraphicsContext2D();
		for(IRenderable entity : RenderableHolder.getInstance().getEntities()) {
			entity.draw(gc);
		}
	}
}
