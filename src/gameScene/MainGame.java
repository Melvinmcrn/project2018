package gameScene;

import area.Table;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;

public class MainGame extends Pane {
	
	private Image bg = new Image(ClassLoader.getSystemResource("images/GameBackground.png").toString());
	
	public MainGame() {
		this.setPrefHeight(480);
		this.setPrefWidth(800);
		this.setBackground(new Background(new BackgroundImage(bg, null, null, null, null)));
		this.setVisible(true);
		
		Table table1 = new Table(2,2);
		
		this.getChildren().add(table1);
	}
	
}
