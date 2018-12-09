package scene;

import button.NavigationButton;
import exception.CharacterNotSelectedException;
import exception.NameRestrictException;
import exception.NameLengthRestrictException;
import exception.NameNotEnteredException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.util.Duration;
import logic.GameLogic;

public class CharacterSelectScene extends StackPane {

	private int charID;
	private String charName;
	private boolean isSelect1 = false;
	private boolean isSelect2 = false;
	private Pane charPane = this;
	private TextField nameTextField;

	private final Image logo = new Image(ClassLoader.getSystemResource("images/CharSelectLogo.png").toString());
	private final Image character1_notSelect = new Image(
			ClassLoader.getSystemResource("images/Player1_notSelect.png").toString());
	private final Image character1_select = new Image(
			ClassLoader.getSystemResource("images/Player1_select.png").toString());
	private final Image character1_mouseOn = new Image(
			ClassLoader.getSystemResource("images/Player1_mouseOn.png").toString());
	private final Image character2_notSelect = new Image(
			ClassLoader.getSystemResource("images/Player2_notSelect.png").toString());
	private final Image character2_select = new Image(
			ClassLoader.getSystemResource("images/Player2_select.png").toString());
	private final Image character2_mouseOn = new Image(
			ClassLoader.getSystemResource("images/Player2_mouseOn.png").toString());
	private final Image nameTextFieldBackground = new Image(
			ClassLoader.getSystemResource("images/NameTextFieldBG.png").toString());

	private GraphicsContext gc;
	private Font NAME_FONT = Font.loadFont(ClassLoader.getSystemResourceAsStream("fonts/Otaku_Rant.ttf"), 30);
	private AudioClip mouseIn = new AudioClip(ClassLoader.getSystemResource("musics/mouseIn.mp3").toExternalForm());
	private AudioClip mouseClicked = new AudioClip(
			ClassLoader.getSystemResource("musics/mouseClicked.mp3").toExternalForm());
	private static Media charSelBG = new Media(
			ClassLoader.getSystemResource("videos/CharacterSelectBG.mp4").toExternalForm());
	private static MediaPlayer charSelPlayer = new MediaPlayer(charSelBG);

	public CharacterSelectScene() {

		// Video Background

		charSelPlayer.setAutoPlay(true);
		MediaView view = new MediaView(charSelPlayer);
		charSelPlayer.setOnEndOfMedia(new Runnable() {
			@Override
			public void run() {
				charSelPlayer.seek(Duration.ZERO);
				charSelPlayer.play();
			}
		});

		// Top Logo
		Canvas logoCanvas = new Canvas(245, 130);
		gc = logoCanvas.getGraphicsContext2D();
		gc.drawImage(logo, 0, 0);

		// Character Select
		charID = 0;
		VBox characterSet = new VBox();
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

		characterSelect.getChildren().addAll(character2, character1);
		characterSelect.setSpacing(50);

		// Name Field
		HBox nameSet = new HBox();
		nameSet.setAlignment(Pos.CENTER);
		nameSet.setSpacing(30);
		StackPane nameField = new StackPane();
		nameTextField = new TextField();
		nameTextField.setFont(NAME_FONT);
		nameTextField.setMaxWidth(300);
		nameTextField.setAlignment(Pos.CENTER);
		nameTextField.getStylesheets().add("assets/CharacterSelect.css");

		// Restrict Space
		nameTextField.textProperty().addListener((observable, old_value, new_value) -> {
			try {
				if ((new_value.substring(new_value.length() - 1).matches("[^A-Z]"))
						&& (new_value.substring(new_value.length() - 1).matches("[^\\d]"))
						&& (new_value.substring(new_value.length() - 1).matches("[^a-z]"))) {
					nameTextField.setText(old_value);
					throw new NameRestrictException();
				}
			} catch (NameRestrictException e) {
				e.showAlert(this);
			} catch (StringIndexOutOfBoundsException e) {
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		addTextLimiter(nameTextField, 12);

		// Add text field background
		Canvas textFieldBackground = new Canvas(340, 70);
		gc = textFieldBackground.getGraphicsContext2D();
		gc.drawImage(nameTextFieldBackground, 0, 0);

		// OK Button
		NavigationButton okButton = new NavigationButton("Ok", "");
		EventHandler<InputEvent> handler = new EventHandler<InputEvent>() {
			public void handle(InputEvent event) {
				try {
					if (nameTextField.getText().length() > 0) {
						charName = nameTextField.getText().toUpperCase();
						nameTextField.setEditable(false);
						System.out.println("Player name is " + charName);
						GameLogic.setPlayerName(charName);
					} else {
						throw new NameLengthRestrictException();
					}
				} catch (NameLengthRestrictException e) {
					e.showAlert(charPane);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		okButton.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);

		// Name Set
		nameField.getChildren().addAll(textFieldBackground, nameTextField);
		nameSet.getChildren().addAll(nameField, okButton);
		characterSet.getChildren().addAll(characterSelect, nameSet);

		// Navigation Button
		NavigationButton nextButton = new NavigationButton("Next", "Main Game") {
			@Override
			protected void setEvent() {
				this.setOnMouseClicked((MouseEvent event) -> {
					try {
						if (getCharID() == 0)
							throw new CharacterNotSelectedException();
						if (getCharName() == null)
							throw new NameNotEnteredException();
						mouseClicked.play();
						System.out.println(name);
						SceneManager.gotoScene(goToScene);
					} catch (CharacterNotSelectedException e) {
						e.showAlert(charPane);
					} catch (NameNotEnteredException e) {
						e.showAlert(charPane);
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
				this.setOnMouseEntered((MouseEvent event) -> {
					mouseIn.play();
					drawButtonGlow();
				});
				this.setOnMouseExited((MouseEvent event) -> {
					drawButton();
				});
			}
		};
		NavigationButton backButton = new NavigationButton("Back", "Welcome");

		HBox navigationBtnBox = new HBox();
		navigationBtnBox.getChildren().addAll(backButton, nextButton);
		navigationBtnBox.setAlignment(Pos.CENTER_LEFT);
		navigationBtnBox.setPadding(new Insets(0, 30, 20, 30));
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

	public static MediaPlayer getVidPlayer() {
		return charSelPlayer;
	}

	public int getCharID() {
		return charID;
	}

	public String getCharName() {
		return charName;
	}

	public boolean isSelect1() {
		return isSelect1;
	}

	public boolean isSelect2() {
		return isSelect2;
	}

	private void setChar(int thisID, Canvas thisCanvas, int otherID, Canvas otherCanvas) {
		thisCanvas.setOnMouseClicked((MouseEvent event) -> {
			this.charID = thisID;
			// this.drawCharacterSelected(thisID, thisCanvas);
			this.drawCharacter(otherID, otherCanvas);
			if (thisID == 1) {
				this.isSelect1 = true;
				this.isSelect2 = false;
				this.charID = 1;
			} else if (thisID == 2) {
				this.isSelect2 = true;
				this.isSelect1 = false;
				this.charID = 2;
			}
			GameLogic.setPlayer(thisID);
			mouseClicked.play();
		});
		thisCanvas.setOnMouseEntered((MouseEvent event) -> {
			this.drawCharacterMouseOn(thisID, thisCanvas);
			mouseIn.play();
		});
		thisCanvas.setOnMouseExited((MouseEvent event) -> {
			if ((!isSelect1 && thisID == 1) || (!isSelect2 && thisID == 2)) {
				this.drawCharacter(thisID, thisCanvas);
			} else {
				this.drawCharacterSelected(thisID, thisCanvas);
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

	private void drawCharacterMouseOn(int ID, Canvas canvas) {
		gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		if (ID == 1)
			gc.drawImage(character1_mouseOn, 0, 0);
		if (ID == 2)
			gc.drawImage(character2_mouseOn, 0, 0);
	}

	public TextField getNameTextField() {
		return nameTextField;
	}

	public void addTextLimiter(final TextField tf, final int maxLength) {
		tf.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(final ObservableValue<? extends String> ov, final String oldValue,
					final String newValue) {
				try {
					if (tf.getText().length() > maxLength) {
						String s = tf.getText().substring(0, maxLength);
						tf.setText(s);
						throw new NameLengthRestrictException();
					}
				} catch (NameLengthRestrictException e) {
					e.showAlert(charPane);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}