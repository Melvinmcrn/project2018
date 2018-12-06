package logic;

import java.util.ArrayList;
import java.util.List;

import area.Area;
import customer.Customer;
import sharedObject.RenderableHolder;

public class GameLogic {
	
	private List<Customer> customerContainer;

	public GameLogic() {
		this.customerContainer = new ArrayList<Customer>();
		
		//Area area = new Area();
		//RenderableHolder.getInstance().add(area);
		
	}
	
	public void logicUpdate() {
		
	}
}
