package data;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class HighScoreData implements Comparable<HighScoreData>{

	private static ObservableList<HighScoreData> data = FXCollections.observableArrayList();
	private String name;
	private int score;

	public HighScoreData(String name, int score) {
		this.name = name;
		this.score = score;
		this.add(this);
	}

	public void add(HighScoreData data) {
		HighScoreData.data.add(data);
		HighScoreData.data.sort(null);
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
	
	public static int size() {
		return data.size();
	}
	
	public static HighScoreData get(int i) {
		return data.get(i);
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

	@Override
	public int compareTo(HighScoreData o) {
		if(this.score == o.score && !this.name.equals(o.name)) return 0;
		if(this.score != o.score) {
			if(this.score > o.score) return -1;
			else return 1;
		}
		return this.name.compareTo(o.name);
	}
}
