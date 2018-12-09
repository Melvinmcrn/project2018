package customer;

import component.Curry;
import logic.GameLogic;

public class Nobita extends Customer {

	public Nobita(int x, int y) {
		super("Nobita", (int) (20*GameLogic.getExtraWaitTime()), "Curry", x, y);
		// TODO Auto-generated constructor stub
	}

}
