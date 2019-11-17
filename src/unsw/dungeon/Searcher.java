package unsw.dungeon;

public interface Searcher {
	/**
	 * perform a search from mover's current position to target tile and return Path
	 * @param mover
	 * @param target
	 * @return
	 */
	public Path search(Movable mover, Tile target);
}
