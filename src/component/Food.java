package component;

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

public abstract class Food extends ImageView {

	protected final String name;
	protected final int x;
	protected final int y;
	protected final int price;
	protected final int cookTime;
	protected final Food thisFood = this;

	protected final String imagePath;
	protected final String imageGlowPath;
	protected final String imageBwPath;
	protected final Image image;
	protected final Image imageGlow;
	protected final Image imageBw;

	protected boolean isCooked;
	protected ProgressBar cookBar;

	public Food(String name, int price, int cookTime, int x, int y) {
		this.name = name;
		this.price = price;
		this.cookTime = cookTime;

		// Set image
		this.imagePath = ClassLoader.getSystemResource("images/" + name + ".png").toString();
		this.imageGlowPath = ClassLoader.getSystemResource("images/" + name + "Glow.png").toString();
		this.imageBwPath = ClassLoader.getSystemResource("images/" + name + "Bw.png").toString();
		this.image = new Image(this.imagePath);
		this.imageGlow = new Image(this.imageGlowPath);
		this.imageBw = new Image(this.imageBwPath);

		// Set position
		this.x = x;
		this.y = y;
		this.setX(x * 80);
		this.setY(y * 80);

		// Set cookBar
		this.cookBar = new ProgressBar(0);
		this.cookBar.setPrefWidth(80);
		this.cookBar.setLayoutX(x * 80);
		this.cookBar.setLayoutY((y * 80) + 65);
		this.isCooked = false;

		this.setEvent();
		this.setFoodImage(3);
		this.startCooking();

	}

	private void startCooking() {
		this.cookBar.setProgress(0);
		this.cookBar.setVisible(true);
		this.setDisable(true);
		this.setFoodImage(3);
		this.cooking();
	}

	private void cooking() {
		Thread cooking = new Thread(() -> {
			try {
				while (this.cookBar.getProgress() < 1) {
					Platform.runLater(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							setProgress();
						}
					});
					Thread.sleep(500);
				}
				this.isCooked = true;
				this.setDisable(false);
				this.cookBar.setVisible(false);
				this.setFoodImage(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		cooking.start();

	}

	private void setFoodImage(int status) {
		// 1 = Normal, 2 = Glow, 3 = Bw
		switch (status) {
		case 1:
			this.setImage(this.image);
			break;
		case 2:
			this.setImage(this.imageGlow);
			break;
		case 3:
			this.setImage(this.imageBw);
			break;
		}

	}

	private void setProgress() {
		double addProgress = 0.5 / this.cookTime;
		double nowProgress = this.cookBar.getProgress();
		if (addProgress + nowProgress > 1) {
			this.cookBar.setProgress(1);
		} else {
			this.cookBar.setProgress(addProgress + nowProgress);
		}
	}

	private void setEvent() {

		this.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				setFoodImage(2);
			}
		});

		this.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				setFoodImage(1);
			}
		});

		this.setOnDragDetected(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				System.out.println("onDragDetected");

				// allow COPY transfer mode
				Dragboard db = thisFood.startDragAndDrop(TransferMode.COPY);

				// put a string on dragboard
				ClipboardContent content = new ClipboardContent();
				content.putString("Food " + name + " " + cookTime);
				db.setContent(content);
				db.setDragView(image);

				event.consume();
			}
		});

		this.setOnDragDone(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				// the drag-and-drop gesture
				// System.out.println(name + " drag done");
				if (event.getTransferMode() == TransferMode.COPY) {
					startCooking();
				}

				event.consume();
			}
		});

	}

	public int getPrice() {
		return price;
	}

	public String getName() {
		return name;
	}

	public boolean isCooked() {
		return isCooked;
	}

	public ProgressBar getCookBar() {
		return cookBar;
	}

}
