package unsw.dungeon;

public class Movable extends Entity {
	
	Dungeon dungeon;
	
	Movable(Dungeon dungeon, int x, int y) {
		super(x, y);
		this.dungeon = dungeon;
	}
    
	public void move(Tile tile) {
		dungeon.moveEntity(this, tile);
	}
}
