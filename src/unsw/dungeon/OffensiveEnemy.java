package unsw.dungeon;

public class OffensiveEnemy implements MovementStrategy {
	
	private AStarSearcher searcher = new AStarSearcher();

	@Override
	public Tile getBestMove(Movable mover) {
		Path searchPath = searcher.search(mover, mover.getDungeon().getPlayer().getCurrentTile());
		if (!searchPath.isEmpty() && searchPath.getNext() != null) {
			return searchPath.getNext();
		}
		return mover.getCurrentTile();
	}

}
