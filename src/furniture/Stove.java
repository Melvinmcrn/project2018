package furniture;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import sharedObject.IRenderable;

public class Stove extends ImageView {
	
	private double x;
	private double y;
	private int isCooking;
	private int isCooked;
	
	public Stove(int x, int y) {
		this.x = x;
		this.y = y;
		this.isCooked = 0;
		this.isCooking = 0;
		
	}
	
	public void setEvent() {
		this.setOnMouseClicked(new event
	}


}
