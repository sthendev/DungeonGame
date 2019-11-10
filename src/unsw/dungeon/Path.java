package unsw.dungeon;

import java.util.List;
import java.util.ArrayList;

public class Path {
	List<Tile> path;
	
	public Path() {
		this.path = new ArrayList<>();
	}
	
	public List<Tile> getPath() {
		return path;
	}
	
	public void add(Tile tile) {
		path.add(tile);
	}
	
	public void addFront(Tile tile) {
		path.add(0, tile);
	}
	
	public boolean isEmpty() {
		return path.isEmpty();
	}
	
	public Tile getEnd() {
		return path.get(path.size() - 1);
	}
	
	public Tile getNext() {
		if (path.size() > 1) return path.get(1);
		return null;
	}
	
	public boolean allTransparent(Movable mover) {
		for (Tile tile : path) {
			if (!tile.allTransparentEntities()) return false;
		}
		return true;
	}
	
}
