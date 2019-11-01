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
		entity.setPosition(null);
	}
	
	public Tile up() {
		return dungeon.getTile(x, y - 1);
	}
	
	public Tile down() {
		return dungeon.getTile(x, y + 1);
	}
	
	public Tile left() {
		return dungeon.getTile(x - 1, y);
	}
	
	public Tile right() {
		return dungeon.getTile(x + 1, y);
	}
	
	public List<Tile> getSurroundingTiles() {
		List<Tile> tiles = new ArrayList<>();
		
		addIfNotNull(tiles, up());
		addIfNotNull(tiles, down());
		addIfNotNull(tiles, left());
		addIfNotNull(tiles, right());
		
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
			entity.meet(mover);
		}
    	dungeon.linkEntityTile(mover, this);
	}
	
	public void movedOff(Movable mover) {
		removeEntity(mover);
		for (Entity entity : entities) {
			entity.leave(mover);
		}
	}
	
	@Override
	public String toString() {
		return "Tile(" + getX() + ", " + getY() + ")";
	}
	
}