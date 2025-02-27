package logic;

import java.util.ArrayList;
import java.util.List;

import component.*;
import customer.*;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import scene.GameScene;

public class GameLogic {

	private static List<Customer> customerContainer;
	private static List<Table> tableContainer;
	private static List<Food> foodContainer;
	private static List<ProgressBar> eatBarContainer;
	private static List<ProgressBar> cookBarContainer;
	private static List<Thread> threadContainer;
	private static Customer[] waitArea = { null, null, null, null, null };
	private static int player = 1;
	private static int money = 0;
	private static int score = 100;
	private static double tipMoney = 1;
	private static double extraWaitTime = 1;
	private static boolean gameOver = false;
	private static String playerName;

	private Customer newCustomer = null;
	private ImageView playerImage;

	private Thread generateCustomerThread;
	private boolean isGenerateCustomerStart = false;

	public GameLogic() {
		GameLogic.customerContainer = new ArrayList<Customer>();
		GameLogic.tableContainer = new ArrayList<Table>();
		GameLogic.foodContainer = new ArrayList<Food>();
		GameLogic.eatBarContainer = new ArrayList<ProgressBar>();
		GameLogic.cookBarContainer = new ArrayList<ProgressBar>();
		GameLogic.threadContainer = new ArrayList<Thread>();

		GameLogic.money = 0;
		GameLogic.score = 100;
		GameLogic.tipMoney = 1;
		GameLogic.extraWaitTime = 1;
		gameOver = false;

		// Set wait are null
		for (int i = 0; i < waitArea.length; i++) {
			waitArea[i] = null;
		}

		// Set table and eat bar
		for (int i = 2; i <= 6; i += 2) {
			for (int j = 2; j <= 4; j += 2) {
				Table table = new Table(i, j);
				tableContainer.add(table);
				eatBarContainer.add(table.getEatBar());
			}
		}

		// Set food and cook bar
		foodContainer.add(new Dorayaki());
		foodContainer.add(new Curry());
		foodContainer.add(new Steak());
		for (int i = 0; i < foodContainer.size(); i++) {
			cookBarContainer.add(foodContainer.get(i).getCookBar());
		}

		// Set player image
		this.playerImage = new ImageView(
				ClassLoader.getSystemResource("images/StatusBar/Player" + player + ".png").toString());
		this.playerImage.setX(20);
		this.playerImage.setY(0);
		// Set player's special action
		switch (player) {
		case 1:
			// Daddy make customer wait longer
			GameLogic.extraWaitTime = 1.5;
			break;
		case 2:
			// Mummy get more money
			GameLogic.tipMoney = 1.5;
			break;
		}

	}

	private void generateCustomer() {
		if (!isGenerateCustomerStart) {
			this.isGenerateCustomerStart = true;
			this.generateCustomerThread = new Thread(() -> {
				try {
					int i = this.getAvailableWaitArea();
					while (i == -1) {
						i = this.getAvailableWaitArea();
					}
					Thread.sleep(5 * 1000);
					newCustomer = this.getRandomCustomer(i + 2, 0);
					isGenerateCustomerStart = false;
					GameLogic.getThreadContainer().remove(generateCustomerThread);
				} catch (InterruptedException e1) {
					
				}
			});
			GameLogic.getThreadContainer().add(generateCustomerThread);
			this.generateCustomerThread.start();
		}
	}

	private Customer getRandomCustomer(int x, int y) {
		int randomNumber = (int) (Math.random() * 5 + 1);
		switch (randomNumber) {
		case 1:
			System.out.println("Generate Doraemon at position " + (x - 1));
			return new Doraemon(x, y);
		case 2:
			System.out.println("Generate Nobita at position " + (x - 1));
			return new Nobita(x, y);
		case 3:
			System.out.println("Generate Giant at position " + (x - 1));
			return new Giant(x, y);
		case 4:
			System.out.println("Generate Shizuka at position " + (x - 1));
			return new Shizuka(x, y);
		default:
			System.out.println("Generate Suneo at position " + (x - 1));
			return new Suneo(x, y);
		}

	}

	private int getAvailableWaitArea() {
		for (int i = 0; i < GameLogic.waitArea.length; i++) {
			if (GameLogic.waitArea[i] == null) {
				return i;
			}
		}
		return -1;
	}

	public void logicUpdate() {
		if (newCustomer != null) {
			// Add new customer
			customerContainer.add(newCustomer);
			waitArea[(int) ((newCustomer.getX() / 80) - 2)] = newCustomer;

			GameScene.getMainGame().getChildren().add(customerContainer.get(customerContainer.size() - 1));
			GameScene.getMainGame().getChildren().add(customerContainer.get(customerContainer.size() - 1).getWaitBar());

			newCustomer = null;
		}

		// Generate new customer
		this.generateCustomer();
	}

	public static void addMoney(int money) {
		GameLogic.money += (money * GameLogic.tipMoney);
	}

	public static void addScore() {
		if (score + 10 > 100) {
			score = 100;
		} else {
			score += 10;
		}
	}

	public static void deductScore() {
		if (score - 10 < 0) {
			score = 0;
		} else {
			score -= 10;
		}
	}

	public static void endGame() {
		gameOver = true;
		for (Thread thread : threadContainer) {
			thread.interrupt();
		}
	}

	public void setTipMoney(double tipMoney) {
		GameLogic.tipMoney = tipMoney;
	}

	public ImageView getPlayerImage() {
		return playerImage;
	}

	public static List<Customer> getCustomerContainer() {
		return customerContainer;
	}

	public static List<Table> getTableContainer() {
		return tableContainer;
	}

	public static List<Food> getFoodContainer() {
		return foodContainer;
	}

	public static List<ProgressBar> getEatBarContainer() {
		return eatBarContainer;
	}

	public static List<ProgressBar> getcookBarContainer() {
		return cookBarContainer;
	}

	public static List<Thread> getThreadContainer() {
		return threadContainer;
	}

	public static Customer[] getWaitArea() {
		return waitArea;
	}

	public static int getMoney() {
		return money;
	}

	public static int getScore() {
		return score;
	}

	public static void setPlayer(int player) {
		GameLogic.player = player;
	}

	public static int getPlayer() {
		return player;
	}

	public static double getExtraWaitTime() {
		return extraWaitTime;
	}

	public static boolean isGameOver() {
		return gameOver;
	}

	public static String getPlayerName() {
		return playerName;
	}

	public static void setPlayerName(String playerName) {
		GameLogic.playerName = playerName;
	}

}
