package sharedObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RenderableHolder {
	
	private static final RenderableHolder instance = new RenderableHolder();
	
	private List<IRenderable> entities;
	private Comparator<IRenderable> comparator;
	
	public RenderableHolder() {
		this.entities = new ArrayList<IRenderable>();
		this.comparator = (IRenderable o1, IRenderable o2) -> {
			if(o1.getZ() > o2.getZ()) {
				return 1;
			} return -1;
		};
	}
	
	public void add(IRenderable entity) {
		System.out.print("entities add ");
		entities.add(entity);
		//	Sort our list by Z
		Collections.sort(this.entities, this.comparator);
	}
	
	public void update() {
		//	Don't forget to implement
	}

	public List<IRenderable> getEntities() {
		return entities;
	}
}
