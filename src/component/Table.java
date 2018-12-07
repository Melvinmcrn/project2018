package component;

import customer.*;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;
import logic.GameLogic;

public class Table extends ImageView {

	private boolean available;
	private double x;
	private double y;
	private Customer customer = null;
	private String customerName = null;
	private int money;
	private String action = ""; // "", Sit, Eat, Bill
	private ProgressBar waitBar;
	private Table thisTable = this;
	private Thread waitThread;

	private static final String tableNormal = ClassLoader.getSystemResource("images/TableNormal.png").toString();
	private static final String tableNormalGlow = ClassLoader.getSystemResource("images/TableNormalGlow.png")
			.toString();

	public Table(int x, int y) {
		super(tableNormal);
		this.x = x;
		this.y = y;
		this.available = true;
		this.money = 0;
		this.setX(x * 80);
		this.setY((y - 1) * 80);
		this.waitBar = new ProgressBar(0);
		this.waitBar.setLayoutX(x * 80);
		this.waitBar.setLayoutY(y * 80);

		this.setEvent();
	}

	public void sit(Customer customer) {
		if (this.isAvailable()) {
			this.customer = customer;
			this.customerName = customer.getName();
			this.action = "Sit";
			this.setTableImage(action);
			this.available = false;
			customer.setVisible(false);
			System.out.println("Customer sit");
			
			this.waiting(this.customer.getWaitTime());
			this.leave();
		}
	}

	private void bill() {
		this.action = "Bill";
		this.setTableImage(this.action);
		
		this.waiting(this.customer.getWaitTime());
		this.leave();
	}
	
	private void checkBill() {
		GameLogic.addMoney(this.money);
		this.leave();
	}

	private void leave() {
		this.customer = null;
		this.customerName = "";
		this.action = "";
		this.available = true;
		this.money = 0;
		this.setTableImage("");
	}

	private void eat() {
		this.action = "Eat";
		this.setTableImage(this.action);
		this.waiting(5);
		this.bill();
	}

	private String getImagePath(String action) {
		System.out.println("Set table image " + action);
		
		if (action.equals("EatGlow")) {
			action = "Eat";
		}

		if (!this.available) {
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
				setTableImage(action + "Glow");
			}

		});

		this.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				setTableImage(action);
			}
		});

		this.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (!isAvailable() && action.equals("Bill")) {
					waitThread.interrupt();
				}
			}
		});

		this.setOnDragOver(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				/* data is dragged over the target */
				System.out.println("onDragOver");

				/*
				 * accept it only if it is not dragged from the same node and if it has a string
				 * data
				 */
				if (event.getGestureSource() != thisTable && event.getDragboard().hasString()) {
					/* allow for moving */
					event.acceptTransferModes(TransferMode.COPY);
				}

				event.consume();
			}
		});

		this.setOnDragEntered(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				/* the drag-and-drop gesture entered the target */
				System.out.println("onDragEntered");
				/* show to the user that it is an actual gesture target */
				if (event.getDragboard().hasString()) {
					setTableImage(action + "Glow");
				}

				event.consume();
			}
		});

		this.setOnDragExited(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				/* mouse moved away, remove the graphical cues */
				setTableImage(action);
				event.consume();
			}
		});

		this.setOnDragDropped(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				/* data dropped */
				System.out.println("onDragDropped");
				/* if there is a string data on dragboard, read it and use it */
				Dragboard db = event.getDragboard();
				boolean success = false;
				if (!action.equals("Sit")) {
					// SHOW MESSAGE THAT IT IS WRONG ACTION
				} else {
					if (db.getString().equals(customer.getFood())) {
						waitThread.interrupt();
						success = true;
					} else {
						// SHOW MESSAGE THAT IT IS WRONG FOOD
					}
				}
				/*
				 * let the source know whether the string was successfully transferred and used
				 */
				event.setDropCompleted(success);
				event.consume();
			}
		});

	}

	private void waiting(int waitTime) {
		waitThread = new Thread(() -> {

			try {
				while (this.waitBar.getProgress() < 1) {
					setProgress(waitTime);
					//System.out.println(action+" "+waitBar.getProgress());
					Thread.sleep(500);
				}

			} catch (InterruptedException e1) {
				e1.printStackTrace();
				if(!this.isAvailable() && this.action.equals("Sit")){
					this.eat();
				} else if(!this.isAvailable() && this.action.equals("Bill")){
					this.checkBill();
				}
			}
		});
		waitThread.start();
	}

	private void setProgress(int waitTime) {
		double addProgress = 0.5 / waitTime;
		double nowProgress = this.waitBar.getProgress();
		if (addProgress + nowProgress > 1) {
			this.waitBar.setProgress(1);
		} else {
			this.waitBar.setProgress(addProgress + nowProgress);
		}
	}

	public void setTableImage(String action) {
		if (this.available) {
			if (action.equals("Glow")) {
				this.setImage(new Image(tableNormalGlow));
			} else {
				this.setImage(new Image(tableNormal));
			}
		} else {
			this.setImage(new Image(this.getImagePath(action)));
		}
	}

	public boolean isAvailable() {
		return available;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getMoney() {
		return money;
	}

	public String getAction() {
		return action;
	}

	public ProgressBar getWaitBar() {
		return waitBar;
	}

	public void setAction(String action) {
		this.action = action;
	}

}
