package unsw.dungeon;

import java.util.List;
import java.util.ArrayList;

public class Movable extends Entity {
	
	Dungeon dungeon;
	
	Movable(Dungeon dungeon, Tile position) {
		super(position);
		this.dungeon = dungeon;
	}
    
	public void moveMe(Tile tile) {
		dungeon.moveEntity(this, tile);
	}
	
	public List<Tile> getValidMoves() {
		List<Tile> validMoves = new ArrayList<>();
		
		for (Tile tile : getPosition().getSurroundingTiles()) {
			if (tile.canMove(this)) validMoves.add(tile);
		}
		
		return validMoves;
	}
}
