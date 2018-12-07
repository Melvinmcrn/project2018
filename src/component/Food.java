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

	protected String name;
	protected int x;
	protected int y;
	protected int price;
	protected int cookTime;
	protected boolean isCooked;
	protected ProgressBar cookBar;

	protected final String imagePath;
	protected final String imageGlowPath;
	protected final Image image;
	protected final Image imageGlow;

	public Food(String name, int price, int cookTime, int x, int y) {
		this.name = name;
		this.price = price;
		this.cookTime = cookTime;
		this.cookBar = new ProgressBar(0);
		this.imagePath = ClassLoader.getSystemResource("images/" + name + ".png").toString();
		this.imageGlowPath = ClassLoader.getSystemResource("images/" + name + "Glow.png").toString();
		this.image = new Image(this.imagePath);
		this.imageGlow = new Image(this.imageGlowPath);
		this.setFoodImage(1);
		this.x = x;
		this.y = y;
		this.setX(x * 80);
		this.setY(y * 80);
		this.isCooked = false;

		this.startCooking();

	}
	
	private void startCooking() {
		this.cookBar.setProgress(0);
		this.cookBar.setVisible(true);
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
				this.cookBar.setVisible(false);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		cooking.start();

	}
	
	private void setFoodImage(int status) {
		// 1 = Normal, 2 = Glow
		switch(status) {
		case 1:
			this.setImage(this.image);
			break;
		default:
			this.setImage(this.imageGlow);
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
	
	private void setAction() {
		
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

				/* allow COPY transfer mode */
				ImageView food = new ImageView(imagePath);
				Dragboard db = food.startDragAndDrop(TransferMode.MOVE);

				/* put a string on dragboard */
				ClipboardContent content = new ClipboardContent();
				content.putString(name);
				db.setContent(content);

				event.consume();
			}
		});
		
		this.setOnDragDone(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				/* the drag-and-drop gesture ended */
				System.out.println("onDragDone");
				
				/* if the data was successfully moved, clear it */
				if (event.getTransferMode() == TransferMode.MOVE) {
					startCooking();
				}

				event.consume();
			}
		});
	}

}
