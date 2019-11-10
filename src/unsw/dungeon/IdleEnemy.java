package unsw.dungeon;

public class IdleEnemy implements MovementStrategy {

	@Override
	public Tile getBestMove(Movable mover) {
		return mover.getCurrentTile();
	}

}
