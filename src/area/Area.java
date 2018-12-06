package area;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class Area implements IRenderable {

	// Field Detail
	// 0 : Normal Area
	// 1 : Area for construct

	private static int[][] field = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };

	public Area() {
		for (int i = 0; i < this.field.length; i++) {
			for (int j = 0; j < this.field[i].length; i++) {
				if (this.field[i][j] == 1) {
					RenderableHolder.getInstance().add(new Stove(i, j, false));
				} else if (this.field[i][j] == 2) {
					RenderableHolder.getInstance().add(new Table(i, j, false));
				}
			}
		}
	}

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
						getTerrain(x, y) * 40, 0, 40, 80);
				gc.drawImage(croppedAreaImage, x * 40, y * 80);
			}
		}

	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return true;
	}

}
