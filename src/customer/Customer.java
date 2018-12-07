package customer;

import javafx.application.Platform;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class Customer extends ImageView {

	protected String name;
	protected String food;
	protected int waitTime;
	protected ProgressBar waitBar;

	protected final String imagePath;
	protected final String imageGlowPath;

	public Customer(String name, int waitTime, String food, int x, int y) {
		super();
		this.name = name;
		this.food = food;
		this.imagePath = ClassLoader.getSystemResource("images/" + name + "/" + name + ".png").toString();
		this.imageGlowPath = ClassLoader.getSystemResource("images/" + name + "/" + name + "Glow.png").toString();
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
					Platform.runLater(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							setProgress();
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

	public String getName() {
		return this.name;
	}

	public String getFood() {
		return food;
	}

	public ProgressBar getWaitBar() {
		return waitBar;
	}

}
