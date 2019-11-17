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
	
	/**
	 * add specified tile to end of path
	 * @param tile
	 */
	public void add(Tile tile) {
		path.add(tile);
	}
	
	/**
	 * add specified tile to front of path
	 * @param tile
	 */
	public void addFront(Tile tile) {
		path.add(0, tile);
	}
	
	/**
	 * 
	 * @return whether or not path is empty
	 */
	public boolean isEmpty() {
		return path.isEmpty();
	}
	
	/**
	 * 
	 * @return get last tile in path
	 */
	public Tile getEnd() {
		return path.get(path.size() - 1);
	}
	
	/**
	 * 
	 * @return get second tile in path return null ig path length < 2
	 */
	public Tile getNext() {
		if (path.size() > 1) return path.get(1);
		return null;
	}
	
	/**
	 * 
	 * @param mover
	 * @return whether or not all tiles in path contain only transparent entities
	 */
	public boolean allTransparent(Movable mover) {
		for (Tile tile : path) {
			if (!tile.allTransparentEntities()) return false;
		}
		return true;
	}
	
}
