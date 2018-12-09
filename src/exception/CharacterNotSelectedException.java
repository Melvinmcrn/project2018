package exception;

public class CharacterNotSelectedException extends AlertException {
	public CharacterNotSelectedException() {
		System.err.println("Please select your character");
		this.exceptionMessage = "Please select your character";
	}
}