package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ScoreData implements Comparable<ScoreData> {

	private static ObservableList<ScoreData> data = FXCollections.observableArrayList();
	private static String filePath = ClassLoader.getSystemResource("data/data.txt").toString();
	private String name;
	private int score;

	public ScoreData(String name, int score) {
		this.name = name;
		this.score = score;
		this.add(this);
	}
	
	private static void writeFile() {
		String dataString = "";
		for(ScoreData x : ScoreData.data) {
			dataString += x.getName() + " " + x.getScore() + "\n";
		}
		
		try {
			FileWriter fileWriter = new FileWriter(new File(filePath));
			fileWriter.write(dataString);
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void readFile() {
		try {
			String directory = System.getProperty("user.home");
			BufferedReader bufferedReader = new BufferedReader(new FileReader("file:///E:\\javacoding\\project2018\\res\\data\\data.txt"));
			String line = bufferedReader.readLine();
			ScoreData.data.clear();
			while (line!=null) {
				line.trim();
				String[] lineSplit = line.split(" ");
				@SuppressWarnings("unused")
				ScoreData addData = new ScoreData(lineSplit[0], Integer.parseInt(lineSplit[1]));
				line = bufferedReader.readLine();
			}
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
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
