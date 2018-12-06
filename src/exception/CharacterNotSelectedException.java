package exception;

public class CharacterNotSelectedException extends Exception {
	public CharacterNotSelectedException() {
		System.err.println("Please select your character");
	}
}
