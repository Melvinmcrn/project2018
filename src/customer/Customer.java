package customer;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import logic.GameLogic;
import scene.GameScene;
import scene.SceneManager;

public abstract class Customer extends ImageView {

	protected String name;
	protected String foodName;
	protected int action; // 1 = wait in queue, 2 = wait for food, 3 = wait for bill
	protected int waitTime;
	protected ProgressBar waitBar;
	protected Thread waitThread;
	protected Customer thisCustomer = this;

	protected final String imagePath;
	protected final String imageGlowPath;
	protected final Image image;
	protected final Image imageGlow;

	public Customer(String name, int waitTime, String foodName, int x, int y) {
		super();
		this.name = name;
		this.foodName = foodName;
		this.setX(x * 80);
		this.setY(y * 80);
		this.imagePath = ClassLoader.getSystemResource("images/" + name + "/" + name + ".png").toString();
		this.imageGlowPath = ClassLoader.getSystemResource("images/" + name + "/" + name + "Glow.png").toString();
		this.image = new Image(this.imagePath);
		this.imageGlow = new Image(this.imageGlowPath);
		this.setImage(this.image);
		this.waitTime = waitTime;

		this.waitBar = new ProgressBar(0);
		this.waitBar.setLayoutX(x * 80);
		this.waitBar.setLayoutY((y * 80) + 80);
		this.waitBar.setPrefWidth(80);
		this.waitBar.setStyle("-fx-accent: LawnGreen");
		this.waitBar.setVisible(false);

		this.setEvent();
		this.waitForTable();
	}

	protected void setCustomerImage(int status) {
		// 1 Normal, 2 Glow
		switch (status) {
		case 1:
			this.setImage(this.image);
			break;
		case 2:
			this.setImage(this.imageGlow);
			break;
		}
	}

	protected void setEvent() {
		this.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				setCustomerImage(2);
			}
		});

		this.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				setCustomerImage(1);
			}
		});

		this.setOnDragDetected(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				System.out.println("onDragDetected");

				// allow COPY transfer mode
				Dragboard db = thisCustomer.startDragAndDrop(TransferMode.MOVE);

				// put this customer on dragboard
				ClipboardContent content = new ClipboardContent();
				content.putString("Customer " + name + " " + ((int) ((thisCustomer.getX() / 80) - 2)));
				db.setContent(content);
				db.setDragView(image);

				event.consume();
			}
		});

		this.setOnDragDone(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				// the drag-and-drop gesture
				if (event.getTransferMode() == TransferMode.MOVE) {
					System.out.println(name + " drag done");
					GameScene.getMainGame().getChildren().remove(thisCustomer);
				}

				event.consume();
			}
		});
	}

	protected void setProgress() {
		double addProgress = 0.5 / this.waitTime;
		double nowProgress = this.waitBar.getProgress();
		if (addProgress + nowProgress > 1) {
			this.waitBar.setProgress(1);
		} else {
			this.waitBar.setProgress(addProgress + nowProgress);
		}
	}

	protected void waiting() {
		this.waitThread = new Thread(() -> {
			try {
				this.waitBar.setProgress(0);
				this.waitBar.setVisible(true);
				while (this.waitBar.getProgress() < 1) {
					this.setProgress();
					Thread.sleep(500);
				}
				this.waitBar.setVisible(false);
				this.angry();
			} catch (InterruptedException e1) {
				// e1.printStackTrace();
				this.waitBar.setVisible(false);
				this.waitBar.setStyle("-fx-accent: LawnGreen");
				System.out.println(this.name + " feel good :D");
			}
		});
		this.waitThread.start();

	}

	public void deductScore() {
		GameLogic.deductScore();
	}

	public void doCustomerFavor() {
		this.waitThread.interrupt();
	}

	public void waitForTable() {
		this.action = 1;
		this.waiting();
	}

	public void waitForFood() {
		this.action = 2;
		this.waiting();
	}

	public void waitForBill() {
		this.action = 3;
		this.waiting();
	}

	public void angry() {
		GameLogic.deductScore();
		this.leave();
	}

	public void leave() {
		GameLogic.getCustomerContainer().remove(this);
		if (this.action == 1) {
			GameLogic.getWaitArea()[(int) ((this.getX() / 80) - 2)] = null;
		}
		
		Platform.runLater(new Runnable() {
		    @Override
		    public void run() {
		    	GameScene.getMainGame().getChildren().remove(thisCustomer);
		    	GameScene.getMainGame().getChildren().remove(waitBar);
		    }
		});
	}

	public String getName() {
		return this.name;
	}

	public String getfoodName() {
		return foodName;
	}

	public ProgressBar getWaitBar() {
		return waitBar;
	}

	public int getWaitTime() {
		return waitTime;
	}

	public void setWaitBarLocation(int x, int y) {
		this.waitBar.setLayoutX(x * 80);
		this.waitBar.setLayoutY((y * 80) + 80);
	}

	public void setAction(int action) {
		this.action = action;
	}

}
