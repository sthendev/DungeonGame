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
	
	public Tile getEnd() {
		return path.get(path.size() - 1);
	}
	
	public boolean allTransparent(Movable mover) {
		for (Tile tile : path) {
			if (!tile.allTransparentEntities()) return false;
		}
		return true;
	}
	
}
