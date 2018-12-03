package scene;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MyButton extends Canvas {

	private String goToScene;
	private String text;
	private Image buttonLogoNormal = new Image(ClassLoader.getSystemResource("images/WelcomeButton.png").toString());
	private Image buttonLogoGlow = new Image(ClassLoader.getSystemResource("images/WelcomeButtonGlow2.png").toString());
	private static double width = 250;
	private static double height = 80;
	private Font TEXT_FONT = new Font("Cordier New", 25);
	private double TEXT_WIDTH;
	private GraphicsContext gc = this.getGraphicsContext2D();

	public MyButton(String text) {
		super(width, height);
		this.text = text;
		FontLoader fl = Toolkit.getToolkit().getFontLoader();
		TEXT_WIDTH = fl.computeStringWidth(text, TEXT_FONT);

		this.goToScene = text;
		this.setEvent();
		this.drawButton();
	}

	private void setEvent() {
		this.setOnMouseClicked((MouseEvent event) -> {
			System.out.println(goToScene);
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

		/*
		 * gc.setFill(Color.BLUE); gc.fillRect(0, 0, width, height);
		 * 
		 * gc.setStroke(Color.BLUE); gc.setLineWidth(5); gc.strokeRect(0, 0, width,
		 * height);
		 */

		gc.setFill(Color.WHITE);
		gc.setFont(TEXT_FONT);
		gc.fillText(text, (width - TEXT_WIDTH) / 2, height / 2);
	}

	private void drawButtonGlow() {
		gc.clearRect(0, 0, width, height);

		gc.drawImage(buttonLogoGlow, 0, 0, width, height);

		/*
		 * gc.setFill(Color.BLUE); gc.fillRect(0, 0, width, height);
		 *
		 * 
		 * gc.setStroke(Color.WHITE); gc.setLineWidth(5); gc.strokeRect(0, 0, width,
		 * height);
		 */

		gc.setFill(Color.WHITE);
		gc.setFont(TEXT_FONT);
		gc.fillText(text, (width - TEXT_WIDTH) / 2, height / 2);
	}
}
