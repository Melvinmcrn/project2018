package exception;

import button.NavigationButton;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import scene.CharacterSelectScene;

public abstract class AlertException extends Exception {

	protected String exceptionMessage;

	public void showAlert(Pane pane) {
		if (pane instanceof CharacterSelectScene)
			((CharacterSelectScene) pane).getNameTextField().setEditable(false);
		AlertMessage alertBox = new AlertMessage(exceptionMessage);
		BorderPane okBox = new BorderPane();
		NavigationButton okButton = new NavigationButton("Ok", "");
		EventHandler<InputEvent> handler = new EventHandler<InputEvent>() {
			public void handle(InputEvent event) {
				if (pane instanceof CharacterSelectScene)
					((CharacterSelectScene) pane).getNameTextField().setEditable(true);
				pane.getChildren().removeAll(alertBox, okBox);
				System.out.println(pane.getChildren().toString());
			}
		};
		okButton.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);
		okBox.setCenter(okButton);
		BorderPane.setMargin(okButton, new Insets(250, 0, 0, 0));
		pane.getChildren().addAll(alertBox, okBox);
	}
}