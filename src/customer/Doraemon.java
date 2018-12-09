package customer;

import logic.GameLogic;
import scene.GameScene;

public class Doraemon extends Customer implements Complainable {

	public Doraemon(int x, int y) {
		super("Doraemon", (int) (17 * GameLogic.getExtraWaitTime()), "Dorayaki", x, y);
	}

	@Override
	public void angry() {
		System.out.println(this.name + " is angry!");
		GameScene.setStatusMessage(this.name + " is angry!");
		GameLogic.deductScore();
		this.waitBar.setStyle("-fx-accent: red");
		keepComplaining();

	}

	@Override
	public void keepComplaining() {
		waiting();
	}

}
