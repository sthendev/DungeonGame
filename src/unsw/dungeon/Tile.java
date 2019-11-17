package unsw.dungeon;

import java.util.List;
import java.util.ArrayList;
import java.awt.geom.Point2D;

public class Tile {
	private int x, y;
	private Dungeon dungeon;
	private List<Entity> entities;
	
	public Tile(Dungeon dungeon, int x, int y) {
		this.dungeon = dungeon;
		this.x = x;
		this.y = y;
		this.entities = new ArrayList<>();
	}
	
	/**
	 * 
	 * @return x position of tile
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * 
	 * @return y position of tile
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * 
	 * @return entities on this tile
	 */
	public List<Entity> getEntities() {
		return entities;
	}
	
	/**
	 * 
	 * @return whether or not a wall exists on tile
	 */
	public boolean hasWall() {
		for (Entity entity : entities) {
			if (entity instanceof Wall) return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @return whether or not a closed door exists on tile
	 */
	public boolean hasClosedDoor() {
		for (Entity entity : entities) {
			if (entity instanceof Door && !((Door) entity).isOpened()) return true;
		}
		return false;
	}
	
	/**
	 * place specified entity on tile
	 * @param entity
	 */
	public void placeEntity(Entity entity) {
		if (entity instanceof Movable) {
			Movable mover = (Movable) entity;
			handleInteractionsComing(mover);
		}
		addEntity(entity);
		entity.setCurrentTile(this);
	}
	
	/**
	 * add entity to entities tracked by this tile
	 * @param entity
	 */
	public void addEntity(Entity entity) {
		entities.add(entity);
	}
	
	/**
	 * remove entity from entities tracked by this tile
	 * @param entity
	 */
	public void removeEntity(Entity entity) {
		entities.remove(entity);
		entity.setCurrentTile(null);
	}
	
	/**
	 * 
	 * @param xMove
	 * @param yMove
	 * @return tile that is the specified x and y offset from this tiles
	 */
	public Tile getAdjacentTile(int xMove, int yMove) {
		return dungeon.getTile(x + xMove, y - yMove);
	}
	
	/**
	 * 
	 * @param tile
	 * @return direction of adjacent tile from this tile
	 */
	public Direction getDirectionOfTile(Tile tile) {
		if (getAdjacentTile(0, 1) != null && getAdjacentTile(0, 1).equals(tile)) {
			return Direction.UP;
		} else if (getAdjacentTile(0, -1) != null && getAdjacentTile(0, -1).equals(tile)) {
			return Direction.DOWN;
		} else if (getAdjacentTile(-1, 0) != null && getAdjacentTile(-1, 0).equals(tile)) {
			return Direction.LEFT;
		} else if (getAdjacentTile(1, 0) != null && getAdjacentTile(1, 0).equals(tile)) {
			return Direction.RIGHT;
		} else {
			return null;
		}
	}
	
	/**
	 * 
	 * @param sourceTile
	 * @return adjacent tile that is in the opposite direction to the specified adjacent source tile
	 */
	public Tile getOppositeTile(Tile sourceTile) {
		Direction sourceDirection = getDirectionOfTile(sourceTile);
		if (sourceDirection == Direction.UP) {
			return getAdjacentTile(0, -1);
		} else if (sourceDirection == Direction.DOWN) {
			return getAdjacentTile(0, 1);
		} else if (sourceDirection == Direction.LEFT) {
			return getAdjacentTile(1, 0);
		} else if (sourceDirection == Direction.RIGHT) {
			return getAdjacentTile(-1, 0);
		} else {
			return null;
		}
	}
	
	/**
	 * 
	 * @return tiles around this tile in four directions
	 */
	public List<Tile> getSurroundingTiles() {
		List<Tile> tiles = new ArrayList<>();
		
		addIfNotNull(tiles, getAdjacentTile(0, 1));
		addIfNotNull(tiles, getAdjacentTile(0, -1));
		addIfNotNull(tiles, getAdjacentTile(-1, 0));
		addIfNotNull(tiles, getAdjacentTile(1, 0));
		
		return tiles;
	}
	
	/**
	 * add to tiles list if specified tile is not null
	 * @param tiles
	 * @param tile
	 */
	private void addIfNotNull(List<Tile> tiles, Tile tile) {
		if (tile != null) tiles.add(tile); 
	}
	
	/**
	 * 
	 * @param mover
	 * @return whether or not Movable entity can move on this tile
	 */
	public boolean canMove(Movable mover) {
		for (Entity entity : entities) {
			if (entity.isBlocking(mover)) return false;
		}
		return true;
	}
	
	/**
	 * 
	 * @param target
	 * @return distance to specified target tile
	 */
	public double distToTile(Tile target) {
    	return Point2D.distance((double) getX(), (double) getY(),
    			(double) target.getX(), (double) target.getY());
    }

	/**
	 * 
	 * @return whether or not all entities on this tile are transparent
	 */
	public boolean allTransparentEntities() {
		for (Entity entity : entities) {
			if (!entity.isTransparent()) return false;
		}
		return true;
	}

	/**
	 * handle interactions of Movable entity moving onto this tile
	 * @param mover
	 */
	public void movedOn(Movable mover) {
		List<Entity> entitiesCopy = new ArrayList<>(entities);
		for (Entity entity : entitiesCopy) {
			entity.notifyComing(mover);
		}
		if (mover.getStillExists() && mover.getCurrentTile() == null) {
			addEntity(mover);
			mover.setCurrentTile(this);
		}
	}
	
	/**
	 * handle interactions of Movable entity moving off this tile
	 * @param mover
	 */
	public void movedOff(Movable mover) {
		removeEntity(mover);
		handleInteractionsLeaving(mover);
	}
	
	/**
	 * process enitity interactions of Movable entity moving onto this tile
	 * @param mover
	 */
	public void handleInteractionsComing(Movable mover) {
		List<Entity> entitiesCopy = new ArrayList<>(entities);
		for (Entity e : entitiesCopy) {
			e.notifyComing(mover);
		}
	}
	
	/**
	 * process entity interactions of Movable entity moving off this tile
	 * @param mover
	 */
	public void handleInteractionsLeaving(Movable mover) {
		List<Entity> entitiesCopy = new ArrayList<>(entities);
		for (Entity entity : entitiesCopy) {
			entity.notifyLeaving(mover);
		}
	}
	
	@Override
	public String toString() {
		return "Tile(" + getX() + ", " + getY() + ")";
	}
	
}