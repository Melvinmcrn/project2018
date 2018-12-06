package area;

import javafx.scene.canvas.GraphicsContext;
import sharedObject.IRenderable;

public class CookingArea implements IRenderable {

	private boolean isConstructed;
	private double x;
	private double y;

	public CookingArea(int x, int y, boolean isConstructed) {
		this.x = x;
		this.y = y;
		this.isConstructed = isConstructed;
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return -8;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return false;
	}

}
