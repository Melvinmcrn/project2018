package customer;

import logic.GameLogic;

public class Giant extends Customer implements Complainable {

	public Giant(int x, int y) {
		super("Giant", (int) (15 * GameLogic.getExtraWaitTime()), "Curry", x, y);
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
