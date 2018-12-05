package button;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import scene.SceneManager;

public abstract class MyButton extends Canvas {

	protected String goToScene;
	protected String name;
	protected String buttonPath;
	protected String buttonGlowPath;
	protected Image buttonLogoNormal;
	protected Image buttonLogoGlow;
	protected final double width;
	protected final double height;
	protected GraphicsContext gc = this.getGraphicsContext2D();

	public MyButton(String name, String toScene, double width, double height) {
		super(width, height);
		this.width = width;
		this.height = height;
		this.name = name;
		this.goToScene = toScene;

		this.buttonPath = ClassLoader.getSystemResource("images/" + this.name + "Button.png").toString();
		this.buttonGlowPath = ClassLoader.getSystemResource("images/" + this.name + "ButtonGlow.png").toString();
		this.buttonLogoNormal = new Image(this.buttonPath);
		this.buttonLogoGlow = new Image(this.buttonGlowPath);

		this.setEvent();
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

	public abstract void drawButton();

	public abstract void drawButtonGlow();
}
