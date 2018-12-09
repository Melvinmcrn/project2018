package customer;

public class Giant extends Customer implements Complainable {

	public Giant(int x, int y) {
		super("Giant", 15, "Curry", x, y);
	}

	@Override
	public void angry() {
		keepComplaining();
	}

	@Override
	public void keepComplaining() {
		waiting();
	}

}
