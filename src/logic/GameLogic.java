package logic;

import java.util.ArrayList;
import java.util.List;

import area.Area;
import customer.Customer;
import sharedObject.RenderableHolder;

public class GameLogic {

	private List<Customer> customerContainer;
	private static int money = 0;
	private static double extraMoney = 1;

	public GameLogic() {
		this.customerContainer = new ArrayList<Customer>();
		money = 0;
		// Area area = new Area();
		// RenderableHolder.getInstance().add(area);

	}

	public static void addMoney(int money) {
		GameLogic.money += (money * GameLogic.extraMoney);
	}

	public void logicUpdate() {

	}

	public void setExtraMoney(double extraMoney) {
		this.extraMoney = extraMoney;
	}
}
