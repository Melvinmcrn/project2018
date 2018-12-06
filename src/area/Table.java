package area;

import customer.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import logic.GameLogic;

public class Table extends Pane {

	private boolean isAvailable;
	private double x;
	private double y;
	private Customer customer = null;
	private String customerName = null;
	private int money;

	private static String tableNormal;
	private static String tableDoraemon;
	private static String tableNobita;
	private static String tableGiant;
	private static String tableShizuka;
	private static String tableSuneo;
	private ImageView image;

	public Table(int x, int y) {
		this.x = x;
		this.y = y;
		this.isAvailable = true;
		this.money = 0;
		this.image = new ImageView(tableNormal);
	}

	private void leave() {
		this.customer = null;
		this.customerName = null;
		this.isAvailable = true;
		this.money = 0;
		this.setImage();
	}

	private void setImage() {
		if(this.isAvailable) {
			this.image.setImage(new Image(tableNormal));
		} else if(this.customerName.equals("Doraemon")) {
			this.image.setImage(new Image(tableDoraemon));
		} else if(this.customerName.equals("Nobita")) {
			this.image.setImage(new Image(tableNobita));
		} else if(this.customerName.equals("Giant")) {
			this.image.setImage(new Image(tableGiant));
		} else if(this.customerName.equals("Shizuka")) {
			this.image.setImage(new Image(tableShizuka));
		} else if(this.customerName.equals("Suneo")) {
			this.image.setImage(new Image(tableSuneo));
		}
	}

	public void sit(Customer customer) {
		if (this.isAvailable) {
			this.customer = customer;
			this.customerName = customer.getName();
			this.isAvailable = false;
		}
	}

	public void checkBill() {
		GameLogic.addMoney(this.money);
		this.leave();
	}

}
