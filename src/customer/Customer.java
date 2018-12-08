package customer;

import component.*;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Customer extends ImageView {

	protected String name;
	protected Food food;
	protected String foodName;
	protected int waitTime;
	protected ProgressBar waitBar;

	protected final String imagePath;
	protected final String imageGlowPath;
	protected final Image image;
	protected final Image imageGlow;

	public Customer(String name, int waitTime, Food food, int x, int y) {
		super();
		this.name = name;
		this.food = food;
		this.setX(x * 80);
		this.setY(y * 80);
		this.imagePath = ClassLoader.getSystemResource("images/" + name + "/" + name + ".png").toString();
		this.imageGlowPath = ClassLoader.getSystemResource("images/" + name + "/" + name + "Glow.png").toString();
		this.image = new Image(this.imagePath);
		this.imageGlow = new Image(this.imageGlowPath);
		this.setImage(this.image);
		this.waitTime = waitTime;
		this.waitBar = new ProgressBar(0);
	}

	private void setProgress() {
		double addProgress = 0.5 / this.waitTime;
		double nowProgress = this.waitBar.getProgress();
		if (addProgress + nowProgress > 1) {
			this.waitBar.setProgress(1);
		} else {
			this.waitBar.setProgress(addProgress + nowProgress);
		}
	}

	public void waitThread() {
		Thread wait = new Thread(() -> {

			try {
				while (this.waitBar.getProgress() < 1) {

					setProgress();

					Thread.sleep(500);
				}

			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		});
		wait.start();

	}

	public String getName() {
		return this.name;
	}

	public Food getFood() {
		return food;
	}

	public String getFoodName() {
		return foodName;
	}

	public ProgressBar getWaitBar() {
		return waitBar;
	}

	public int getWaitTime() {
		return waitTime;
	}

}
