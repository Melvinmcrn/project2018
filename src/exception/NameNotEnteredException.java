package exception;

public class NameNotEnteredException extends AlertException {
	public NameNotEnteredException() {
		System.err.println("Please enter player name and click OK");
		this.exceptionMessage = "Please enter player name and click OK";
	}
}