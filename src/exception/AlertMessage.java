package exception;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class AlertMessage extends StackPane {

	private ImageView alertBG = new ImageView(
			new Image(ClassLoader.getSystemResource("alert/AlertBox.png").toString()));
	private ImageView blackBG = new ImageView(new Image(ClassLoader.getSystemResource("alert/BlackBG.png").toString()));
	private Label printError;
	private Font TEXT_FONT = Font.loadFont(ClassLoader.getSystemResourceAsStream("fonts/Otaku_Rant.ttf"), 18);

	public AlertMessage(String message) {
		// OK Button
		printError = new Label(message);
		printError.setPrefWidth(250);
		printError.setWrapText(true);
		printError.setTextAlignment(TextAlignment.CENTER);
		printError.setPadding(new Insets(30, 0, 0, 0));
		printError.setFont(TEXT_FONT);
		BorderPane posLabel = new BorderPane();
		posLabel.setCenter(printError);
		this.getChildren().addAll(blackBG, alertBG, posLabel);
	}
}