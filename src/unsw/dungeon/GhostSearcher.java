package unsw.dungeon;

import java.util.List;

public class GhostSearcher implements Searcher {

	@Override
	public Path search(Movable mover, Tile target) {
		Path path = new Path();
		path.add(mover.getCurrentTile());
		while (path.getEnd() != target) {
			Tile closest = getClosestToTarget(path.getEnd(), target);
			path.add(closest);
		}
		return path;
	}
	
	/**
	 * 
	 * @param cur
	 * @param target
	 * @return tile around current tile that is closest to target tile
	 */
	private Tile getClosestToTarget(Tile cur, Tile target) {
		List<Tile> surroundingTiles = cur.getSurroundingTiles();
		Tile closest = cur;
		double closestDist = cur.distToTile(target);
		for (Tile tile : surroundingTiles) {
			double newDist = tile.distToTile(target);
			if (newDist < closestDist) {
				closest = tile;
				closestDist = newDist;
			} else if (newDist == closestDist) {
				if (!closest.allTransparentEntities() && tile.allTransparentEntities()) {
					closest = tile;
				}
			}
		}
		return closest;
	}
	
}
