package unsw.dungeon;

public interface MovementStrategy {
	/**
	 * 
	 * @param mover
	 * @return desired Tile to move to
	 */
	public Tile getBestMove(Movable mover);
}
