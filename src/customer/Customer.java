package customer;

import javafx.application.Platform;
import javafx.scene.control.ProgressBar;

public abstract class Customer {
	
	protected String image;
	protected double tipMoney;
	protected int waitTime;
	protected ProgressBar waitBar;
	
	public Customer(String image, double tipMoney, int waitTime) {
		super();
		this.image = image;
		this.tipMoney = tipMoney;
		this.waitTime = waitTime;
		this.waitBar = new ProgressBar(0);
	}
	
	public void waitThread() {
		Thread thread = new Thread(() -> {

			try {/*
				while (this.potionBar.getProgress() < 1) {
					Platform.runLater(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							potionBar.setProgress(potionBar.getProgress() + 0.002);
						}
					});
					Thread.sleep(16); */
				}

			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		});
		
	}
	
}
