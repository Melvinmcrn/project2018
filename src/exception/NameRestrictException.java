package exception;

public class NameRestrictException extends AlertException {
	public NameRestrictException() {
		System.err.println("Player Name should be A - Z or 0 - 9");
		this.exceptionMessage = "Player Name should be A - Z or 0 - 9";
	}
}