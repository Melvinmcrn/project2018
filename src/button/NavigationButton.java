package button;

import javafx.scene.input.MouseEvent;
import scene.SceneManager;

public class NavigationButton extends MyButton {

	public NavigationButton(String name, String toScene) {
		super(name, toScene, 50, 50);
		this.drawButton();
		this.setEvent();
	}

	@Override
	protected void setEvent() {
		this.setOnMouseClicked((MouseEvent event) -> {
			System.out.println(name);
			SceneManager.gotoScene(goToScene);
			mouseClicked.play();
		});
		this.setOnMouseEntered((MouseEvent event) -> {
			drawButtonGlow();
			mouseIn.play();
		});
		this.setOnMouseExited((MouseEvent event) -> {
			drawButton();
		});
	}

	@Override
	public void drawButton() {
		gc.clearRect(0, 0, width, height);
		gc.drawImage(buttonLogoNormal, 0, 0, width, height);

	}

	@Override
	public void drawButtonGlow() {
		gc.clearRect(0, 0, width, height);
		gc.drawImage(buttonLogoGlow, 0, 0, width, height);

	}

}