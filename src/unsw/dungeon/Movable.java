package unsw.dungeon;

public class Movable extends Entity {
	
	Dungeon dungeon;
	
	Movable(Dungeon dungeon, Tile position) {
		super(position);
		this.dungeon = dungeon;
	}
    
	public void move(Tile tile) {
		dungeon.moveEntity(this, tile);
	}
}
