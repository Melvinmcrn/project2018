package scene;

import button.NavigationButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class CharacterSelectScene extends StackPane {

	private int charID;
	private String charName;
	private boolean isSelect1 = false;
	private boolean isSelect2 = false;
	private Image logo = new Image(ClassLoader.getSystemResource("images/CharSelectLogo.png").toString());
	private Image okButton = new Image(ClassLoader.getSystemResource("images/OkButton.png").toString());
	private Image character1_notSelect = new Image(ClassLoader.getSystemResource("images/Player1_notSelect.png").toString());
	private Image character1_select = new Image(ClassLoader.getSystemResource("images/Player1_select.png").toString());
	private Image character2_notSelect = new Image(ClassLoader.getSystemResource("images/Player2_notSelect.png").toString());
	private Image character2_select = new Image(ClassLoader.getSystemResource("images/Player2_select.png").toString());
	private Image nameTextFieldBackground = new Image(ClassLoader.getSystemResource("images/NameTextFieldBackground.png").toString());
	private GraphicsContext gc;
	private Font NAME_FONT = Font.loadFont(ClassLoader.getSystemResourceAsStream("fonts/Otaku_Rant.ttf"), 30);

	public CharacterSelectScene() {

		// Video Background
		Media media = new Media(ClassLoader.getSystemResource("videos/CharacterSelectBackground.mp4").toExternalForm());
		MediaPlayer player = new MediaPlayer(media);
		player.setAutoPlay(true);
		MediaView view = new MediaView(player);
		player.setOnEndOfMedia(new Runnable() {
			@Override
			public void run() {
				player.seek(Duration.ZERO);
				player.play();
			}
		});

		// Top Logo
		Canvas logoCanvas = new Canvas(245, 130);
		gc = logoCanvas.getGraphicsContext2D();
		gc.drawImage(logo, 0, 0);

		// Character Select
		charID = 0;
		VBox characterSet = new VBox();
		//characterSet.setPadding(new Insets(40, 0, 0, 130));
		characterSet.setPadding(new Insets(40, 0, 0, 0));
		characterSet.setAlignment(Pos.CENTER);
		characterSet.setSpacing(20);

		HBox characterSelect = new HBox();
		characterSelect.setAlignment(Pos.CENTER);

		Canvas character1 = new Canvas(250, 297);
		gc = character1.getGraphicsContext2D();
		gc.drawImage(character1_notSelect, 0, 0);

		Canvas character2 = new Canvas(250, 297);
		gc = character2.getGraphicsContext2D();
		gc.drawImage(character2_notSelect, 0, 0);

		setChar(1, character1, 2, character2);
		setChar(2, character2, 1, character1);

		characterSelect.getChildren().addAll(character1, character2);
		characterSelect.setSpacing(50);

		// Name Set
		HBox nameSet = new HBox();
		StackPane nameField = new StackPane();
		TextField nameTextField = new TextField();
		nameTextField.setFont(NAME_FONT);
		nameTextField.setMaxWidth(300);
		nameTextField.setAlignment(Pos.CENTER);
		nameTextField.getStylesheets().add("assets/CharacterSelect.css");
		
		//Restrict Space
		nameTextField.textProperty().addListener(
			     (observable, old_value, new_value) -> {
			          if(new_value.contains(" "))
			          {
			                nameTextField.setText(old_value); 
			          }
			     }
			);
		addTextLimiter(nameTextField, 12);
		
		//Add background
		Canvas textFieldBackground = new Canvas(340, 70);
		gc = textFieldBackground.getGraphicsContext2D();
		gc.drawImage(nameTextFieldBackground, 0, 0);
		
		//OK button;
		Canvas okButtonCanvas = new Canvas(70, 70);
		gc = okButtonCanvas.getGraphicsContext2D();
		gc.drawImage(okButton, 0, 0);
		
		nameField.getChildren().addAll(textFieldBackground ,nameTextField);
		
		characterSet.getChildren().addAll(characterSelect, nameSet);

		// Navigation Button
		NavigationButton nextButton = new NavigationButton("Next", "Main Game");
		NavigationButton backButton = new NavigationButton("Back", "Welcome");

		HBox navigationBtnBox = new HBox();
		navigationBtnBox.getChildren().addAll(backButton, nextButton);
		navigationBtnBox.setAlignment(Pos.CENTER_LEFT);
		navigationBtnBox.setPadding(new Insets(20, 30, 20, 30));
		navigationBtnBox.setSpacing(650);

		// BorderPane
		BorderPane borderPane = new BorderPane();

		borderPane.setTop(logoCanvas);
		BorderPane.setAlignment(logoCanvas, Pos.TOP_CENTER);

		borderPane.setCenter(characterSet);
		BorderPane.setAlignment(characterSet, Pos.CENTER);

		borderPane.setBottom(navigationBtnBox);
		BorderPane.setAlignment(navigationBtnBox, Pos.BOTTOM_CENTER);

		this.getChildren().addAll(view, borderPane);
	}

	public int getCharID() {
		return charID;
	}

	public void setCharID(int charID) {
		this.charID = charID;
	}

	public String getCharName() {
		return charName;
	}

	public void setCharName(String charName) {
		this.charName = charName;
	}

	private void setChar(int thisID, Canvas thisCanvas, int otherID, Canvas otherCanvas) {
		thisCanvas.setOnMouseClicked((MouseEvent event) -> {
			this.charID = thisID;
			this.drawCharacterSelected(thisID, thisCanvas);
			this.drawCharacter(otherID, otherCanvas);
			if (thisID == 1) {
				this.isSelect1 = true;
				this.isSelect2 = false;
				this.setCharID(1);
			} else if (thisID == 2) {
				this.isSelect2 = true;
				this.isSelect1 = false;
				this.setCharID(2);
			}
		});
		thisCanvas.setOnMouseEntered((MouseEvent event) -> {
			this.drawCharacterSelected(thisID, thisCanvas);
		});
		thisCanvas.setOnMouseExited((MouseEvent event) -> {
			if ((!isSelect1 && thisID == 1) || (!isSelect2 && thisID == 2)) {
				this.drawCharacter(thisID, thisCanvas);
			}
		});
	}

	private void drawCharacter(int ID, Canvas canvas) {
		gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		if (ID == 1) {
			gc.drawImage(character1_notSelect, 0, 0);
		} else if (ID == 2) {
			gc.drawImage(character2_notSelect, 0, 0);
		}
	}

	private void drawCharacterSelected(int ID, Canvas canvas) {
		gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		if (ID == 1)
			gc.drawImage(character1_select, 0, 0);
		if (ID == 2)
			gc.drawImage(character2_select, 0, 0);
	}
	
	public static void addTextLimiter(final TextField tf, final int maxLength) {
		tf.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
				if (tf.getText().length() > maxLength) {
					String s = tf.getText().substring(0, maxLength);
					tf.setText(s);
				}
			}
		});
	}
}