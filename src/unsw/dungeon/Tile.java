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
	
	public int getY() {
		return y;
	}
	
	public int getX() {
		return x;
	}
	
	public List<Entity> getEntities() {
		return entities;
	}
	
	public boolean hasWall() {
		for (Entity entity : entities) {
			if (entity instanceof Wall) return true;
		}
		return false;
	}
	
	public boolean hasClosedDoor() {
		for (Entity entity : entities) {
			if (entity instanceof Door && !((Door) entity).isOpened()) return true;
		}
		return false;
	}
	
	public void placeEntity(Entity entity) {
		if (entity instanceof Movable) {
			Movable mover = (Movable) entity;
			handleInteractionsComing(mover);
		}
		addEntity(entity);
		entity.setCurrentTile(this);
	}
	
	public void addEntity(Entity entity) {
		entities.add(entity);
	}
	
	public void removeEntity(Entity entity) {
		entities.remove(entity);
		entity.setCurrentTile(null);
	}
	
	public Tile getAdjacentTile(int xMove, int yMove) {
		return dungeon.getTile(x + xMove, y - yMove);
	}
	
	public Direction getDirectionOfTile(Tile tile) {
		if (getAdjacentTile(0, 1).equals(tile)) {
			return Direction.UP;
		} else if (getAdjacentTile(0, -1).equals(tile)) {
			return Direction.DOWN;
		} else if (getAdjacentTile(-1, 0).equals(tile)) {
			return Direction.LEFT;
		} else if (getAdjacentTile(1, 0).equals(tile)) {
			return Direction.RIGHT;
		} else {
			return null;
		}
	}
	
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
	
	public List<Tile> getSurroundingTiles() {
		List<Tile> tiles = new ArrayList<>();
		
		addIfNotNull(tiles, getAdjacentTile(0, 1));
		addIfNotNull(tiles, getAdjacentTile(0, -1));
		addIfNotNull(tiles, getAdjacentTile(-1, 0));
		addIfNotNull(tiles, getAdjacentTile(1, 0));
		
		return tiles;
	}
	
	private void addIfNotNull(List<Tile> tiles, Tile tile) {
		if (tile != null) tiles.add(tile); 
	}
	
	public boolean canMove(Movable mover) {
		for (Entity entity : entities) {
			if (entity.isBlocking(mover)) return false;
		}
		return true;
	}
	
	public double distToTile(Tile target) {
    	return Point2D.distance((double) getX(), (double) getY(),
    			(double) target.getX(), (double) target.getY());
    }

	public boolean allTransparentEntities() {
		for (Entity entity : entities) {
			if (!entity.isTransparent()) return false;
		}
		return true;
	}

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
	
	public void movedOff(Movable mover) {
		removeEntity(mover);
		handleInteractionsLeaving(mover);
	}
	
	public void handleInteractionsComing(Movable mover) {
		List<Entity> entitiesCopy = new ArrayList<>(entities);
		for (Entity e : entitiesCopy) {
			e.notifyComing(mover);
		}
	}
	
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