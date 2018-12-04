package scene;

import data.HighScoreData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class HighScore extends BorderPane {

	private Label nameHeader;
	private Label scoreHeader;
	private Font HEADER_FONT = Font.loadFont(ClassLoader.getSystemResourceAsStream("fonts/Otaku Rant Bold.ttf"), 30);
	private TableView<HighScoreData> table;
	private TableColumn<HighScoreData, String> nameColumn;
	private TableColumn<HighScoreData, Integer> scoreColumn;
	private Image logo = new Image(ClassLoader.getSystemResource("images/HighScoreLogo.png").toString());
	private Image bg = new Image(ClassLoader.getSystemResource("images/HighScoreBackground.jpg").toString());
	private GraphicsContext gc;
	private Canvas scoreCanvas;
	private Font SCORE_FONT = Font.loadFont(ClassLoader.getSystemResourceAsStream("fonts/Otaku_Rant.ttf"), 30);
	private NavigationButton homeButton;

	@SuppressWarnings({ "deprecation", "unchecked" })
	public HighScore() {

		// Draw logo
		Canvas logoCanvas = new Canvas(245, 130);
		gc = logoCanvas.getGraphicsContext2D();
		gc.drawImage(logo, 0, 0);
/*
		HighScoreData d = new HighScoreData("a", 100);
		HighScoreData e = new HighScoreData("a", 200);
		HighScoreData f = new HighScoreData("a", 300);

		HighScoreData a = new HighScoreData("a", 150);
		HighScoreData b = new HighScoreData("a", 120);
		HighScoreData c = new HighScoreData("a", 100);
*/
		// Create Table Header
		nameHeader = new Label("Name");
		nameHeader.setFont(HEADER_FONT);
		nameHeader.setTextFill(Color.rgb(76, 47, 39));
		scoreHeader = new Label("Score");
		scoreHeader.setFont(HEADER_FONT);
		scoreHeader.setTextFill(Color.rgb(76, 47, 39));

		// Create Table Header Box
		HBox header = new HBox(350);
		header.setAlignment(Pos.CENTER);
		header.setPadding(new Insets(20, 0, 60, 7));
		header.getChildren().addAll(nameHeader, scoreHeader);

		// Create Score Data
		scoreCanvas = new Canvas(681, 248);
		gc = scoreCanvas.getGraphicsContext2D();
		String name1 = "NONE";
		String name2 = "NONE";
		String name3 = "NONE";
		String score1 = "NONE";
		String score2 = "NONE";
		String score3 = "NONE";

		if (HighScoreData.size() >= 1) {
			name1 = HighScoreData.get(0).getName();
			score1 = Integer.toString(HighScoreData.get(0).getScore());
		}
		if (HighScoreData.size() >= 2) {
			name2 = HighScoreData.get(1).getName();
			score2 = Integer.toString(HighScoreData.get(1).getScore());
		}
		if (HighScoreData.size() >= 3) {
			name3 = HighScoreData.get(2).getName();
			score3 = Integer.toString(HighScoreData.get(2).getScore());
		}
		gc.setFill(Color.BLACK);
		// gc.fillRect(0, 0, 681, 248);
		gc.setFont(SCORE_FONT);
		gc.fillText(name1, 10, 50);
		gc.fillText(name2, 10, 126);
		gc.fillText(name3, 10, 202);
		gc.clearRect(235, 0, 446, 248);
		gc.fillText(score1, 441, 50);
		gc.fillText(score2, 441, 126);
		gc.fillText(score3, 441, 202);
		gc.clearRect(671, 0, 10, 248);

		// Create Center Pane
		VBox center = new VBox();
		center.getChildren().addAll(header, scoreCanvas);
		center.setPrefSize(681, 340);
		center.setAlignment(Pos.TOP_CENTER);

		// Draw Home Button
		this.homeButton = new NavigationButton("Home");

		this.setTop(logoCanvas);
		HighScore.setAlignment(logoCanvas, Pos.TOP_CENTER);
		HighScore.setMargin(logoCanvas, new Insets(0, 10, 0, 10));

		this.setCenter(center);
		HighScore.setAlignment(center, Pos.TOP_CENTER);
		HighScore.setMargin(center, new Insets(40, 10, 0, 10));

		this.setBottom(this.homeButton);
		HighScore.setAlignment(this.homeButton, Pos.CENTER);
		HighScore.setMargin(this.homeButton, new Insets(0, 5, 25, 0));

		this.setBackground(new Background(new BackgroundImage(bg, null, null, null, null)));

	}
}
