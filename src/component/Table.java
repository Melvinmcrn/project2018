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

	private final int x;
	private final int y;
	private final Table thisTable = this;

	private Customer customer = null;
	private String customerName = null;
	private int eatTime = 0;
	private int money;
	private String action = ""; // "", Sit, Eat, Bill
	private ProgressBar eatBar;
	private boolean available;

	private Thread eatThread;

	private static final String tableNormal = ClassLoader.getSystemResource("images/TableNormal.png").toString();
	private static final String tableNormalGlow = ClassLoader.getSystemResource("images/TableNormalGlow.png")
			.toString();

	public Table(int x, int y) {
		super(tableNormal);
		this.x = x;
		this.y = y;
		this.available = true;
		this.money = 0;
		this.setX(this.x * 80);
		this.setY((this.y - 1) * 80);

		this.eatBar = new ProgressBar(0);
		this.eatBar.setLayoutX(x * 80);
		this.eatBar.setLayoutY((y * 80) + 80);
		this.eatBar.setPrefWidth(80);
		this.eatBar.setVisible(false);

		this.setEvent();
	}

	private void sit(Customer customer) {
		if (this.isAvailable()) {
			this.customer = customer;
			this.customerName = customer.getName();
			this.action = "Sit";
			this.setTableImage(action);
			this.available = false;
			customer.setVisible(false);
			System.out.println(this.customerName + " sit done");
			
			this.customer.waitForFood();
		}
	}

	private void bill() {
		this.action = "Bill";
		this.setTableImage(this.action);
		
		this.customer.waitForBill();
	}

	private void checkBill() {
		GameLogic.addMoney(this.money);
		this.leave();
		this.customer.leave();
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
		System.out.println(this.customerName + " in eat action");
		this.action = "Eat";
		
		int foodPrice;
		if(this.customer.getfoodName().equals("Dorayaki")) {
			foodPrice = new Dorayaki().getPrice();
		} else if(this.customer.getfoodName().equals("Curry")) {
			foodPrice = new Curry().getPrice();
		} else {
			foodPrice = new Steak().getPrice();
		}
		this.money += foodPrice;
		
		this.setTableImage(this.action);
		this.eating(this.eatTime);
	}

	private String getImagePath(String action) {
		// System.out.println("Set table image " + action);

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
					// eatThread.interrupt();
					checkBill();
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
					event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
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
				System.err.println("content is " + db.getString());
				String[] content = db.getString().split(" ");
				boolean success = false;

				if (content[0].equals("Food")) {
					System.out.println("Food dropped");
					// It is food
					if (!action.equals("Sit")) {
						// SHOW MESSAGE THAT IT IS WRONG ACTION
						System.err.println("Customer is not waiting for food!");
					} else if (!customer.getfoodName().equals(content[1])) {
						// SHOW MESSAGE THAT IT IS WRONG FOOD
						System.err.println("Wrong food!");
					} else {
						// START EATING
						// EAT TIME = COOK TIME
						System.out.println(content[1] + " is served");
						eatTime = Integer.parseInt(content[2]);
						customer.doCustomerFavor();
						success = true;
						eat();
					}

				} else {
					System.out.println("Customer dropped");
					// It is customer
					if (isAvailable()) {
						int i = Integer.parseInt(content[2]);
						sit(GameLogic.getWaitArea()[i]);
						GameLogic.getWaitArea()[i] = null;
						customer.setWaitBarLocation(x, y);
						customer.doCustomerFavor();
						success = true;
						System.out.println(content[1] + " is seated");
					} else {
						// SHOW THAT IT IS NOT AVAILABLE
						System.err.println("Table is not available");
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

	private void eating(int eatTime) {
		System.out.println(this.customerName + " is eating");
		eatThread = new Thread(() -> {

			try {
				this.eatBar.setVisible(true);
				while (this.eatBar.getProgress() < 1) {
					setProgress(eatTime);
					Thread.sleep(500);
				}
				this.eatBar.setVisible(false);
				bill();

			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		});
		eatThread.start();
	}

	private void setProgress(int waitTime) {
		double addProgress = 0.5 / waitTime;
		double nowProgress = this.eatBar.getProgress();
		if (addProgress + nowProgress > 1) {
			this.eatBar.setProgress(1);
		} else {
			this.eatBar.setProgress(addProgress + nowProgress);
		}
	}

	private void setTableImage(String action) {
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

	public ProgressBar getEatBar() {
		return eatBar;
	}

}
