package customer;

import component.Curry;

public class Giant extends Customer {

	public Giant(int x, int y) {
		super("Giant", 15, new Curry(), x, y);
	}

}
