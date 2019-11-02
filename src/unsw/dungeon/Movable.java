package unsw.dungeon;

import java.util.List;
import java.util.ArrayList;

public class Movable extends Entity {
	
	private Dungeon dungeon;
	private Tile prevPosition;
	private boolean stillExists;
	
	Movable(Dungeon dungeon, Tile position) {
		super(position);
		this.dungeon = dungeon;
		this.stillExists = true;
	}
	
	public Dungeon getDungeon() {
		return dungeon;
	}
	
	public Tile getPrevPosition() {
		return prevPosition;
	}
	
	public boolean getStillExists() {
		return stillExists;
	}
	
	public void ceaseExistence() {
		this.stillExists = false;
	}
    
	public void moveMe(Tile tile) {
		dungeon.moveEntity(this, tile);
	}
	
	public List<Tile> getValidMoves() {
		List<Tile> validMoves = new ArrayList<>();
		
		for (Tile tile : getCurrentTile().getSurroundingTiles()) {
			if (tile.canMove(this)) validMoves.add(tile);
		}
		
		return validMoves;
	}
	
	@Override
	public void setCurrentTile(Tile tile) {
		this.prevPosition = getCurrentTile();
		super.setCurrentTile(tile);
	}
}
