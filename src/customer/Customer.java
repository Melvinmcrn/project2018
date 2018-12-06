package customer;

import javafx.application.Platform;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class Customer extends Pane {

	protected String name;
	protected ImageView image;
	protected double tipMoney;
	protected int waitTime;
	protected ProgressBar waitBar;

	public Customer(String name, double tipMoney, int waitTime) {
		super();
		this.name = name;
		this.image = new ImageView(ClassLoader.getSystemResource("images/" + name + "/" + name + ".png").toString());
		this.tipMoney = tipMoney;
		this.waitTime = waitTime;
		this.waitBar = new ProgressBar(0);
	}

	public void waitThread() {
		Thread thread = new Thread(() -> {

			try {
				while (this.waitBar.getProgress() < 1) {
					Platform.runLater(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							waitBar.setProgress(waitBar.getProgress() + 0.002);
						}
					});
					Thread.sleep(16);
				}

			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		});

	}

	public String getName() {
		return this.name;
	}

}
