package scene;

import data.HighScoreData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
	private NavigationButton homeButton;

	@SuppressWarnings({ "deprecation", "unchecked" })
	public HighScore() {

		this.setBackground(new Background(new BackgroundImage(bg, null, null, null, null)));

		// Draw logo
		Canvas logoCanvas = new Canvas(245, 130);
		gc = logoCanvas.getGraphicsContext2D();
		gc.drawImage(logo, 0, 0);

		HighScoreData d = new HighScoreData("a", 100);
		HighScoreData e = new HighScoreData("a", 100);
		HighScoreData f = new HighScoreData("a", 100);

		// Create Table Header
		nameHeader = new Label("Name");
		nameHeader.setFont(HEADER_FONT);
		scoreHeader = new Label("Score");
		scoreHeader.setFont(HEADER_FONT);

		// Create Table Header Box
		HBox header = new HBox(350);
		header.setAlignment(Pos.CENTER);
		header.setPadding(new Insets(50, 0, 0, 7));
		header.getChildren().addAll(nameHeader, scoreHeader);

		// Create Name Column
		nameColumn = new TableColumn<HighScoreData, String>("Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<HighScoreData, String>("name"));
		nameColumn.setResizable(false);
		nameColumn.setEditable(false);
		nameColumn.setSortable(false);
		nameColumn.impl_setReorderable(false);

		// Create Score Column
		scoreColumn = new TableColumn<HighScoreData, Integer>("Score");
		scoreColumn.setCellValueFactory(new PropertyValueFactory<HighScoreData, Integer>("score"));
		scoreColumn.setResizable(false);
		scoreColumn.setEditable(false);
		scoreColumn.setSortable(false);
		scoreColumn.impl_setReorderable(false);

		// Set Table
		table = new TableView<HighScoreData>();
		table.getColumns().addAll(nameColumn,scoreColumn);
		table.setItems(HighScoreData.getData());
		table.setEditable(false);
		table.setPrefHeight(300);
		table.setMaxWidth(656);
		table.getStylesheets().addAll(getClass().getResource("/assets/HighScore.css").toExternalForm());
		
		Font.loadFont(ClassLoader.getSystemResourceAsStream("fonts/Otaku_Rant.ttf"), 25);
		// Create Label when there is no Data
		table.setPlaceholder(new Label(""));

		nameColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.5));
		scoreColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.5));
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table.setPadding(new Insets(35, 0, 0, 0));

		// Create Center Pane
		VBox center = new VBox();
		center.getChildren().addAll(header, table);
		center.setPrefSize(681, 340);
		center.setAlignment(Pos.CENTER);

		// Draw Home Button
		this.homeButton = new NavigationButton("Home");

		HighScoreData a = new HighScoreData("a", 100);
		HighScoreData b = new HighScoreData("a", 100);
		HighScoreData c = new HighScoreData("a", 100);

		Insets insets = new Insets(10);
		this.setTop(logoCanvas);
		HighScore.setAlignment(logoCanvas, Pos.CENTER);
		HighScore.setMargin(logoCanvas, new Insets(0, 10, 10, 10));
		this.setCenter(center);
		HighScore.setAlignment(table, Pos.CENTER);
		HighScore.setMargin(table, new Insets(55, 10, 10, 10));
		this.setBottom(this.homeButton);
		HighScore.setAlignment(this.homeButton, Pos.CENTER);
		HighScore.setMargin(this.homeButton, insets);

	}
	/*
	 * private class Table extends TableView<HighScoreData> {
	 * 
	 * @SuppressWarnings({ "unchecked", "rawtypes" }) public Table(TableColumn
	 * column) { this.getColumns().add(column); this.setPrefSize(250, 245);
	 * this.setItems(HighScoreData.getData()); this.setEditable(false);
	 * this.getStylesheets().addAll(getClass().getResource("/assets/HighScore.css").
	 * toExternalForm());
	 * 
	 * // Create Label when there is no Data Label noDataText = new
	 * Label("No Score Record"); noDataText.setFont(new Font("Tahoma", 50));
	 * noDataText.setTextFill(Color.WHITE); this.setPlaceholder(noDataText);
	 * 
	 * }
	 * 
	 * }
	 */
}
