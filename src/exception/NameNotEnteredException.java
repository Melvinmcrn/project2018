package exception;

public class NameNotEnteredException extends Exception {
	public NameNotEnteredException() {
		System.err.println("Please enter player name and click OK");
	}
}
