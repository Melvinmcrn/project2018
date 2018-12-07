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

	private boolean isAvailable;
	private double x;
	private double y;
	private Customer customer = null;
	private String customerName = null;
	private int money;
	private boolean isClicked;
	private String action = null; // null, Sit, Eat, Bill
	private ProgressBar waitBar;

	private static final String tableNormal = ClassLoader.getSystemResource("images/TableNormal.png").toString();
	private static final String tableNormalGlow = ClassLoader.getSystemResource("images/TableNormalGlow.png")
			.toString();

	public Table(int x, int y) {
		super(tableNormal);
		this.x = x;
		this.y = y;
		this.isAvailable = true;
		this.money = 0;
		this.setX(x * 80);
		this.setY((y - 1) * 80);
		this.waitBar = new ProgressBar(0);
		this.isClicked = false;

		this.setEvent();
	}

	private void sit(Customer customer) {
		if (this.isAvailable) {
			this.customer = customer;
			this.customerName = customer.getName();
			this.action = "Sit";
			this.isAvailable = false;
			customer.setVisible(false);
		}
	}

	private void checkBill() {
		GameLogic.addMoney(this.money);
		this.leave();
	}

	private void leave() {
		this.customer = null;
		this.customerName = null;
		this.action = null;
		this.isAvailable = true;
		this.money = 0;
		this.setTableImage("");
	}

	private void eat() {
		this.action = "Eat";

	}

	private void setTableImage(String action) {
		if (this.isAvailable) {
			if (action.equals("Glow")) {
				this.setImage(new Image(tableNormalGlow));
			} else {
				this.setImage(new Image(tableNormal));
			}
		} else {
			this.setImage(new Image(this.getImagePath(action)));
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
				if (action.equals("Bill")) {
					checkBill();
				}
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
						eat();
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
		Thread wait = new Thread(() -> {

			try {
				while (this.waitBar.getProgress() < 1) {
					Platform.runLater(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							setProgress(waitTime);
						}
					});
					Thread.sleep(500);
				}

			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		});
		wait.start();
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

}
