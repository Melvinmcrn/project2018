package gameScene;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class MainGame extends Pane {
	
	private GraphicsContext gc;
	private Image bg = new Image(ClassLoader.getSystemResource("images/GameBackground.png").toString());
	
	public MainGame() {
		this.setPrefHeight(480);
		this.setPrefWidth(800);
		this.setBackground(new Background(new BackgroundImage(bg, null, null, null, null)));
		this.setVisible(true);
		//this.paintComponent();
	}
	
	public void paintComponent() {
		//gc = this.getGraphicsContext2D();
		for(IRenderable entity : RenderableHolder.getInstance().getEntities()) {
			entity.draw(gc);
		}
	}
}
