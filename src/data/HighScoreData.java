package data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class HighScoreData {

	private static ObservableList<HighScoreData> data = FXCollections.observableArrayList();
	private String name;
	private int score;

	public HighScoreData(String name, int score) {
		this.name = name;
		this.score = score;
		data.add(this);
	}

	public void add(HighScoreData data) {
		HighScoreData.data.add(data);
	}

	public void remove(String name) {
		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).name.equals(name)) {
				data.remove(i);
			}
		}

	}

	public boolean isExist(String name) {
		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).name.equals(name)) {
				return true;
			}
		}
		return false;
	}

	public static ObservableList<HighScoreData> getData() {
		return data;
	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}
}
