package exception;

public class NameLengthRestrictException extends Exception {
	public NameLengthRestrictException() {
		System.err.println("Player Name should have 1 - 12 characters long");
	}
}
