package customer;

import logic.GameLogic;

public class Suneo extends Customer {

	public Suneo(int x, int y) {
		super("Suneo", (int) (22 * GameLogic.getExtraWaitTime()), "Steak", x, y);
	}

}
