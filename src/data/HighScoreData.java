package data;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class HighScoreData implements Comparable<HighScoreData>{

	private static ObservableList<HighScoreData> data = FXCollections.observableArrayList();
	private SimpleStringProperty name;
	private SimpleIntegerProperty score;

	public HighScoreData(String name, int score) {
		this.name = new SimpleStringProperty (name);
		this.score = new SimpleIntegerProperty (score);
		this.add(this);
	}

	public void add(HighScoreData data) {
		HighScoreData.data.add(data);
		HighScoreData.data.sort(null);
	}

	public void remove(String name) {
		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).name.get().equals(name)) {
				data.remove(i);
			}
		}

	}

	public boolean isExist(String name) {
		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).name.get().equals(name)) {
				return true;
			}
		}
		return false;
	}

	public static ObservableList<HighScoreData> getData() {
		return data;
	}

	public String getName() {
		return name.get();
	}

	public int getScore() {
		return score.get();
	}

	@Override
	public int compareTo(HighScoreData o) {
		if(this.score.get() == o.score.get() && !this.name.get().equals(o.name.get())) return 0;
		if(this.score.get() != o.score.get()) {
			if(this.score.get() > o.score.get()) return -1;
			else return 1;
		}
		return this.name.get().compareTo(o.name.get());
	}
}
