package button;

import com.sun.javafx.tk.Toolkit;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

@SuppressWarnings("restriction")
public class TextButton extends MyButton {

	private Font TEXT_FONT = Font.loadFont(ClassLoader.getSystemResourceAsStream("fonts/Otaku Rant Bold.ttf"), 25);
	private double TEXT_WIDTH;

	public TextButton(String name) {
		super("Welcome", name, 250, 80);
		TEXT_WIDTH = Toolkit.getToolkit().getFontLoader().computeStringWidth(super.goToScene, TEXT_FONT);
		this.drawButton();
	}

	@Override
	public void drawButton() {
		gc.clearRect(0, 0, width, height);
		gc.drawImage(buttonLogoNormal, 0, 0, width, height);

		gc.setFill(Color.rgb(251, 205, 109));
		gc.setFont(TEXT_FONT);
		gc.fillText(super.goToScene, (width - TEXT_WIDTH) / 2, (height / 2) + 5);
	}

	@Override
	public void drawButtonGlow() {
		gc.clearRect(0, 0, width, height);

		gc.drawImage(buttonLogoGlow, 0, 0, width, height);

		gc.setFill(Color.WHITE);
		gc.setFont(TEXT_FONT);
		gc.fillText(super.goToScene, (width - TEXT_WIDTH) / 2, (height / 2) + 5);

	}
}
