package unsw.dungeon;

import java.util.List;
import java.util.ArrayList;

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
	
	public void placeEntity(Entity entity) {
		entities.add(entity);
	}
	
	public void removeEntity(Entity entity) {
		entities.remove(entity);
		entity.setCurrentTile(null);
	}
	
	public Tile getAdjacentTile(int xMove, int yMove) {
		return dungeon.getTile(x + xMove, y + yMove);
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
		for (Entity entity: entities) {
			if (entity.isBlocking(mover)) return false;
		}
		return true;
	}
	
	public void movedOn(Movable mover) {
		List<Entity> entitiesCopy = new ArrayList<>(entities);
		for (Entity entity : entitiesCopy) {
			entity.notifyComing(mover);
		}
		if (mover.getStillExists()) dungeon.addEntity(mover, this.x, this.y);
	}
	
	public void movedOff(Movable mover) {
		removeEntity(mover);
		for (Entity entity : entities) {
			entity.notifyLeaving(mover);
		}
	}
	
	@Override
	public String toString() {
		return "Tile(" + getX() + ", " + getY() + ")";
	}
	
}