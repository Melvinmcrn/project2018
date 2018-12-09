package scene;

import button.NavigationButton;
import data.ScoreData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class HallOfFameScene extends StackPane {

	private Label nameHeader;
	private Label scoreHeader;
	private Font HEADER_FONT = Font.loadFont(ClassLoader.getSystemResourceAsStream("fonts/Otaku Rant Bold.ttf"), 30);
	private Image logo = new Image(ClassLoader.getSystemResource("images/HallOfFameLogo.png").toString());
	private static Media highScoreBG = new Media(
			ClassLoader.getSystemResource("videos/HallOfFameBG.mp4").toExternalForm());
	private static MediaPlayer highScorePlayer = new MediaPlayer(highScoreBG);
	private GraphicsContext gc;
	private Canvas scoreCanvas;
	private Font SCORE_FONT = Font.loadFont(ClassLoader.getSystemResourceAsStream("fonts/Otaku_Rant.ttf"), 30);
	private NavigationButton homeButton;

	public HallOfFameScene() {

		highScorePlayer.setAutoPlay(true);
		MediaView view = new MediaView(highScorePlayer);
		highScorePlayer.setOnEndOfMedia(new Runnable() {
			@Override
			public void run() {
				highScorePlayer.seek(Duration.ZERO);
				highScorePlayer.play();
			}
		});

		// Draw logo
		Canvas logoCanvas = new Canvas(245, 130);
		gc = logoCanvas.getGraphicsContext2D();
		gc.drawImage(logo, 0, 0);

		ScoreData.readFile();

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
		String name1 = "NO RECORD";
		String name2 = "NO RECORD";
		String name3 = "NO RECORD";
		String score1 = "NO RECORD";
		String score2 = "NO RECORD";
		String score3 = "NO RECORD";

		if (ScoreData.size() >= 1) {
			name1 = ScoreData.get(0).getName();
			score1 = Integer.toString(ScoreData.get(0).getScore());
		}
		if (ScoreData.size() >= 2) {
			name2 = ScoreData.get(1).getName();
			score2 = Integer.toString(ScoreData.get(1).getScore());
		}
		if (ScoreData.size() >= 3) {
			name3 = ScoreData.get(2).getName();
			score3 = Integer.toString(ScoreData.get(2).getScore());
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
		BorderPane setPosPane = new BorderPane();

		this.homeButton = new NavigationButton("Home", "Welcome");

		setPosPane.setTop(logoCanvas);
		BorderPane.setAlignment(logoCanvas, Pos.TOP_CENTER);
		BorderPane.setMargin(logoCanvas, new Insets(0, 10, 0, 10));

		setPosPane.setCenter(center);
		BorderPane.setAlignment(center, Pos.TOP_CENTER);
		BorderPane.setMargin(center, new Insets(40, 10, 0, 10));

		setPosPane.setBottom(this.homeButton);
		BorderPane.setAlignment(this.homeButton, Pos.CENTER);
		BorderPane.setMargin(this.homeButton, new Insets(0, 5, 25, 0));

		this.getChildren().addAll(view, setPosPane);
	}

	public static MediaPlayer getVidPlayer() {
		return highScorePlayer;
	}
}