package customer;

import logic.GameLogic;

public class Doraemon extends Customer implements Complainable {

	public Doraemon(int x, int y) {
		super("Doraemon", (int) (17 * GameLogic.getExtraWaitTime()), "Dorayaki", x, y);
	}

	@Override
	public void angry() {
		GameLogic.deductScore();
		this.waitBar.setStyle("-fx-accent: red");
		keepComplaining();

	}

	@Override
	public void keepComplaining() {
		waiting();
	}

}
