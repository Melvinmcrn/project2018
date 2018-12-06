package area;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class Area implements IRenderable {
	/*
	 * Field Detail
	 * 0 : Normal Area
	 * 1 : Stove Area
	 * 2 : 2 Table Floor 1/2
	 * 3 : 2 Table Floor 2/2
	 * 4 : 4 Table Floor 1/4
	 * 5 : 4 Table Floor 2/4
	 * 6 : 4 Table Floor 3/4
	 * 7 : 4 Table Floor 4/4
	 */
	private static int[][] field = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 2, 3, 0, 0, 0, 0, 0, 4, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };

	public int getTerrain(int x, int y) {
		if (x < 0 || x >= field[0].length || y < 0 || y >= field.length)
			return 0;
		return field[y][x];
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return -9999;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		for (int x = 0; x <= field[0].length; x++) {
			for (int y = 0; y <= field.length; y++) {
				WritableImage croppedAreaImage = new WritableImage(RenderableHolder.areaImage.getPixelReader(),
						getTerrain(x, y) * 40, 0, 40, 40);
				gc.drawImage(croppedAreaImage, x * 64, y * 64);
			}
		}

	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return true;
	}

}
