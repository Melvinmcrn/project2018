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
	protected Food thisFood = this;

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
		this.setEvent();

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
		switch (status) {
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
				content.putString(name);
				db.setContent(content);

				event.consume();
			}
		});
		/*
		 * this.setOnMouseDragged(new EventHandler<MouseEvent>() {
		 * 
		 * @Override public void handle(MouseEvent event) {
		 * System.out.println(event.getX());
		 * thisFood.setTranslateX(event.getX()-startDragX);
		 * thisFood.setTranslateY(event.getY()-startDragY); } });
		 * 
		 * 
		 * this.setOnMousePressed(new EventHandler<MouseEvent>() {
		 * 
		 * @Override public void handle(MouseEvent e) { orgSceneX = e.getSceneX();
		 * orgSceneY = e.getSceneY();
		 * 
		 * 
		 * orgSceneX = e.getSceneX(); orgSceneY = e.getSceneY(); orgTranslateX = ((Food)
		 * (e.getSource())).getTranslateX(); orgTranslateY = ((Food)
		 * (e.getSource())).getTranslateY(); } });
		 * 
		 * this.setOnMouseDragged(new EventHandler<MouseEvent>() {
		 * 
		 * @Override public void handle(MouseEvent e) {
		 * 
		 * double deltaX = e.getSceneX() - orgSceneX; double deltaY = e.getSceneY() -
		 * orgSceneY; relocate(getLayoutX() + deltaX, getLayoutY() + deltaY); orgSceneX
		 * = e.getSceneX(); orgSceneY = e.getSceneY();
		 * 
		 * 
		 * double offsetX = e.getSceneX() - orgSceneX; double offsetY = e.getScreenX() -
		 * orgSceneY; double newTranslateX = orgTranslateX + offsetX; double
		 * newTranslateY = orgTranslateY + offsetY;
		 * 
		 * ((Food) (e.getSource())).setTranslateX(newTranslateX); ((Food)
		 * (e.getSource())).setTranslateX(newTranslateY); } });
		 */

		this.setOnDragDone(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				// the drag-and-drop gesture
				System.out.println("onDragDone");

				startCooking();

				event.consume();
			}
		});

	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public boolean isCooked() {
		return isCooked;
	}

}
