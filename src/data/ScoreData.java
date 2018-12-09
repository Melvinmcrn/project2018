package data;

import java.io.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ScoreData implements Comparable<ScoreData> {

	private static ObservableList<ScoreData> data = FXCollections.observableArrayList();
	private static File scoreFile = new File("highscore.dat");
	private String name;
	private int score;

	public ScoreData(String name, int score) {
		this.name = name;
		this.score = score;
		this.add(this);
	}

	private static void writeFile() {
		String dataString = "";
		for (ScoreData x : ScoreData.data) {
			dataString += x.getName() + " " + x.getScore() + " ";
			// System.out.println("dataString = " + dataString + " ");
		}
		if (!scoreFile.exists()) {
			try {
				scoreFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		FileWriter writeFile = null;
		BufferedWriter writer = null;
		try {
			writeFile = new FileWriter(scoreFile);
			writer = new BufferedWriter(writeFile);
			writer.write(dataString);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (Exception e) {
			}
		}
	}

	public static void readFile() {
		String line = null;
		FileReader readFile = null;
		BufferedReader reader = null;

		try {
			readFile = new FileReader(scoreFile);
			reader = new BufferedReader(readFile);
			line = reader.readLine();
			if (line != null) {
				ScoreData.data.clear();
				line.trim();
				String[] lineSplit = line.split(" ");
				int size = lineSplit.length;
				for (int i = 0; i < size / 2; i++) {
					@SuppressWarnings("unused")
					ScoreData addData = new ScoreData(lineSplit[i * 2], Integer.parseInt(lineSplit[(i * 2) + 1]));
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			try {
				scoreFile.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			System.out.println("Error Reading file");
		}
	}

	public void add(ScoreData data) {
		ScoreData.data.add(data);
		ScoreData.data.sort(null);
		ScoreData.writeFile();
	}

	public void remove(String name) {
		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).name.equals(name)) {
				data.remove(i);
			}
		}
		ScoreData.writeFile();

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

	public static ScoreData get(int i) {
		return data.get(i);
	}

	public static ObservableList<ScoreData> getData() {
		return data;
	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}

	@Override
	public int compareTo(ScoreData o) {
		if (this.score == o.score && !this.name.equals(o.name))
			return 0;
		if (this.score != o.score) {
			if (this.score > o.score)
				return -1;
			else
				return 1;
		}
		return this.name.compareTo(o.name);
	}
}