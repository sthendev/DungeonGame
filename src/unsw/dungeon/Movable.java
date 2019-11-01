package unsw.dungeon;

import java.util.List;
import java.util.ArrayList;

public class Movable extends Entity {
	
	private Dungeon dungeon;
	private Tile prevPosition;
	
	Movable(Dungeon dungeon, Tile position) {
		super(position);
		this.dungeon = dungeon;
	}
	
	public Dungeon getDungeon() {
		return dungeon;
	}
	
	public Tile getPrevPosition() {
		return prevPosition;
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
	
	@Override
	public void setPosition(Tile tile) {
		this.prevPosition = getPosition();
		super.setPosition(tile);
	}
}
