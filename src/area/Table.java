package area;

import customer.*;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import logic.GameLogic;

public class Table extends ImageView {

	private boolean isAvailable;
	private double x;
	private double y;
	private Customer customer = null;
	private String customerName = null;
	private int money;
	private boolean isClicked;
	private String action = null;

	private static final String tableNormal = ClassLoader.getSystemResource("images/TableNormal.png").toString();
	private static final String tableNormalBW = ClassLoader.getSystemResource("images/TableNormalBw.png").toString();

	public Table(int x, int y) {
		super(tableNormal);
		this.x = x;
		this.y = y;
		this.isAvailable = true;
		this.money = 0;
		this.setX(x * 80);
		this.setY(y * 80);
		this.isClicked = false;
		this.setEvent();
	}

	private void leave() {
		this.customer = null;
		this.customerName = null;
		this.action = null;
		this.isAvailable = true;
		this.money = 0;
		this.setImage("");
	}
	
	private void eat() {
		this.action = "Eat";
		
	}

	private void setImage(String action) {
		if (this.isAvailable) {
			if (action.equals("Bw")) {
				this.setImage(new Image(tableNormalBW));
			} else {
				this.setImage(new Image(tableNormal));
			}
		} else {
			this.setImage(new Image(this.getImagePath(action)));
			if (action.equals("Sit") || action.equals("SitBw") || action.equals("Eat") || action.equals("EatBw")
					|| action.equals("Bill") || action.equals("BillBw")) {
				this.setX((this.x - 1) * 80);
				this.setY((this.y - 1) * 80);
			} else {
				this.setX((this.x) * 80);
				this.setY((this.y) * 80);
			}
		}
	}

	private String getImagePath(String action) {
		if (!this.isAvailable) {
			return ClassLoader
					.getSystemResource("images/" + this.customerName + "/" + this.customerName + action + ".png")
					.toString();
		} else {
			return Table.tableNormal;
		}
	}

	private void setEvent() {
		this.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {

			}

		});

		this.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(isClicked) {
					setImage("");
					isClicked = false;
					System.out.println("Unclick table X:"+x+" Y:"+y);
				} else {
					setImage("Bw");
					isClicked = true;
					System.out.println("Click table X:"+x+" Y:"+y);
				}

			}
		});

		this.setOnMouseDragOver(new EventHandler<MouseDragEvent>() {
			@Override
			public void handle(MouseDragEvent event) {

			}
		});
	}

	public void sit(Customer customer) {
		if (this.isAvailable) {
			this.customer = customer;
			this.customerName = customer.getName();
			this.action = "Sit";
			this.isAvailable = false;
			customer.setVisible(false);
		}
	}

	public void checkBill() {
		GameLogic.addMoney(this.money);
		this.leave();
	}

}
