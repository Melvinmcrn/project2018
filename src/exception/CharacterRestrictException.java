package exception;

public class CharacterRestrictException extends Exception {
	public CharacterRestrictException() {
		System.err.println("Player Name cannot be space");
	}
}
