package scene;

import data.HighScoreData;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class HighScore extends VBox{
	
	private TableView table;
	private TableColumn nameColumn;
	private TableColumn scoreColumn;
	
	public HighScore() {
		/*
		this.setAlignment(Pos.CENTER);
		//this.setBackground(value);
		
		table = new TableView();
		
		nameColumn = new TableColumn("Name");
		nameColumn.setCellValueFactory(
				new PropertyValueFactory<HighScoreData, String>("name"));
		
		scoreColumn = new TableColumn("Highest Score");
		scoreColumn.setCellValueFactory(
				new PropertyValueFactory<HighScoreData, int>("score"));
		
		table.setEditable(false);
		table.getColumns().addAll(nameColumn, scoreColumn);
		
		VBox dataList = new VBox();
		
		this.getChildren().add(scrollPane);
		*/
	}

}
