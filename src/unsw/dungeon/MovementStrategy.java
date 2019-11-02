package unsw.dungeon;

public interface MovementStrategy {
	public Tile getBestMove(Movable mover);
}
