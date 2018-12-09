package customer;

import component.Dorayaki;
import logic.GameLogic;

public class Shizuka extends Customer {

	public Shizuka(int x, int y) {
		super("Shizuka", (int) (25*GameLogic.getExtraWaitTime()),"Dorayaki", x, y);
		// TODO Auto-generated constructor stub
	}

}
