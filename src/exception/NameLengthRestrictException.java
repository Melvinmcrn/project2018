package exception;

public class NameLengthRestrictException extends AlertException {
	public NameLengthRestrictException() {
		System.err.println("Player Name should have 1 - 12 characters long");
		this.exceptionMessage = "Player Name should have 1 - 12 characters long";
	}
}