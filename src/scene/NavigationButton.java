package scene;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

public class NavigationButton extends Canvas{
	private String goToScene;
	private String name;
	private String buttonPath;
	private String buttonGlowPath;
	private Image buttonLogoNormal;
	private Image buttonLogoGlow;
	private static double width = 50;
	private static double height = 50;
	private GraphicsContext gc = this.getGraphicsContext2D();

	public NavigationButton(String name, String scene) {
		super(width, height);
		this.name = name;
		this.goToScene = scene;
		if(name.equals("Next")) {
			this.buttonPath = ClassLoader.getSystemResource("images/NextButton.png").toString();
			this.buttonGlowPath = ClassLoader.getSystemResource("images/NextButtonGlow.png").toString();
		} else if(name.equals("Back")) {
			this.buttonPath = ClassLoader.getSystemResource("images/BackButton.png").toString();
			this.buttonGlowPath = ClassLoader.getSystemResource("images/BackButtonGlow.png").toString();
		} else if(name.equals("Home")) {
			this.buttonPath = ClassLoader.getSystemResource("images/HomeButton.png").toString();
			this.buttonGlowPath = ClassLoader.getSystemResource("images/HomeButtonGlow.png").toString();
		}
		this.buttonLogoNormal = new Image(this.buttonPath);
		this.buttonLogoGlow = new Image(this.buttonGlowPath);
		this.setEvent();
		this.drawButton();
	}

	private void setEvent() {
		this.setOnMouseClicked((MouseEvent event) -> {
			System.out.println(name);
			SceneManager.gotoScene(goToScene);
		});
		this.setOnMouseEntered((MouseEvent event) -> {
			drawButtonGlow();
		});
		this.setOnMouseExited((MouseEvent event) -> {
			drawButton();
		});
	}

	private void drawButton() {
		gc.clearRect(0, 0, width, height);
		gc.drawImage(buttonLogoNormal, 0, 0, width, height);
	}

	private void drawButtonGlow() {
		gc.clearRect(0, 0, width, height);
		gc.drawImage(buttonLogoGlow, 0, 0, width, height);
	}
}
