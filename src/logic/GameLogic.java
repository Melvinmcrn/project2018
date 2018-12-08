package logic;

import java.util.ArrayList;
import java.util.List;

import component.*;
import customer.*;
import javafx.event.EventHandler;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import scene.GameScene;

public class GameLogic {

	private static List<Customer> customerContainer;
	private static List<Table> tableContainer;
	private static List<Food> foodContainer;
	private static List<ProgressBar> eatBarContainer;
	private static List<ProgressBar> cookBarContainer;
	private Customer newCustomer = null;
	private static Customer[] waitArea = { null, null, null, null, null };
	private static int money = 0;
	private static int score = 100;
	private static double tipMoney = 1;
	private static long generateTime = 5;

	private Thread generateCustomerThread;
	private boolean isThreadRunning = false;

	public GameLogic() {
		GameLogic.customerContainer = new ArrayList<Customer>();
		money = 0;
		tableContainer = new ArrayList<Table>();
		foodContainer = new ArrayList<Food>();
		eatBarContainer = new ArrayList<ProgressBar>();
		cookBarContainer = new ArrayList<ProgressBar>();
		this.initialize();
	}

	private void initialize() {
		for (int i = 2; i <= 6; i += 2) {
			for (int j = 2; j <= 4; j += 2) {
				Table table = new Table(i, j);
				tableContainer.add(table);
				eatBarContainer.add(table.getEatBar());
			}
		}
		foodContainer.add(new Dorayaki());
		foodContainer.add(new Curry());
		foodContainer.add(new Steak());
		
		for(int i=0; i<foodContainer.size(); i++) {
			cookBarContainer.add(foodContainer.get(i).getCookBar());
		}

		// tableContainer.get(0).sit(new Doraemon(1, 1));

	}

	private void generateCustomer() {
		if (!isThreadRunning) {
			// System.out.println("Thread is not running");
			isThreadRunning = true;
			generateCustomerThread = new Thread(() -> {
				try {
					// isThreadRunning = true;
					// System.out.println("Start generating customer thread");
					Thread.sleep(GameLogic.generateTime * 1000);
					int i = this.getAvailableWaitArea();
					while (i == -1) {
						i = this.getAvailableWaitArea();
					}
					// System.out.println("Generate customer at position " + i);
					newCustomer = this.getRandomCustomer(i + 2, 0);
					isThreadRunning = false;
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			});
			generateCustomerThread.start();
		}
	}

	private Customer getRandomCustomer(int x, int y) {
		int randomNumber = (int) (Math.random() * 5 + 1);
		switch (randomNumber) {
		case 1:
			System.out.println("Generate Doraemon at " + x + " " + y);
			return new Doraemon(x, y);
		case 2:
			System.out.println("Generate Nobita at " + x + " " + y);
			return new Nobita(x, y);
		case 3:
			System.out.println("Generate Giant at " + x + " " + y);
			return new Giant(x, y);
		case 4:
			System.out.println("Generate Shizuka at " + x + " " + y);
			return new Shizuka(x, y);
		default:
			System.out.println("Generate Suneo at " + x + " " + y);
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
		if (score == 0) {
			//	GAME OVER
		}

		if (newCustomer != null) {
			// System.out.println("Add new customer to container");
			customerContainer.add(newCustomer);
			// System.out.println((int) ((newCustomer.getX()/80)-2));
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

	public void setTipMoney(double tipMoney) {
		GameLogic.tipMoney = tipMoney;
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

	public static Customer[] getWaitArea() {
		return waitArea;
	}
}
