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
	
}
