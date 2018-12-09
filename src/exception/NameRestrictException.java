package exception;

public class NameRestrictException extends Exception {
	public NameRestrictException() {
		System.err.println("Player Name should be A - Z or 0 - 9");
	}
}