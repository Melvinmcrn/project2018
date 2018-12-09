package customer;

public class Doraemon extends Customer implements Complainable{

	public Doraemon(int x, int y) {
		super("Doraemon", 17, "Dorayaki", x, y);
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
