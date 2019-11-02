package unsw.dungeon;

import java.util.List;

public class OffensiveEnemy implements MovementStrategy {

	@Override
	public Tile getBestMove(Movable mover) {
		List<Tile> validMoves = mover.getValidMoves();
    	Tile bestMove = mover.getCurrentTile();
    	
    	for (Tile tile : validMoves) {
    		if (mover.getDungeon().distToPlayer(tile) < mover.getDungeon().distToPlayer(bestMove)) {
    			bestMove = tile;
    		}
    	}
    	
    	return bestMove;
	}

}
