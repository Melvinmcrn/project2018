package logic;

import java.util.ArrayList;
import java.util.List;

import customer.*;
import furniture.*;

public class GameLogic {

	private List<Customer> customerContainer;
	private static List<Table> tableContainer;
	private static List<Stove> stoveContainer;
	private static int money = 0;
	private static double tipMoney = 1;

	public GameLogic() {
		this.customerContainer = new ArrayList<Customer>();
		money = 0;
		tableContainer = new ArrayList<Table>();
		stoveContainer = new ArrayList<Stove>();
		this.initialize();
		
	}
	
	private void initialize() {
		tableContainer.add(new Table(2,2));
		tableContainer.add(new Table(4, 2));
		tableContainer.add(new Table(6, 2));
		tableContainer.add(new Table(2, 4));
		tableContainer.add(new Table(4, 4));
		tableContainer.add(new Table(6, 4));
	}
	
	private Customer getRandomCustomer() {
		int randomNumber;
		switch (randomNumber) {
		case 1:
			return new Doraemon();
		case 2:
			return new Nobita();
		case 3:
			return new Giant();
		case 4:
			return new Shizuka();
		default :
			return new Suneo();
		}
	
	}

	public static void addMoney(int money) {
		GameLogic.money += (money * GameLogic.tipMoney);
	}

	public void logicUpdate() {

	}

	public void setTipMoney(double tipMoney) {
		this.tipMoney = tipMoney;
	}

	public List<Customer> getCustomerContainer() {
		return customerContainer;
	}

	public static List<Table> getTableContainer() {
		return tableContainer;
	}
	
	public static List<Stove> getStoveContainer(){
		return stoveContainer;
	}
}
