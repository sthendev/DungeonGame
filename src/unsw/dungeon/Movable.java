package unsw.dungeon;

import java.util.List;
import java.util.ArrayList;

public abstract class Movable extends Entity {
	
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
	
	public boolean canMove(Tile tile) {
		return tile.canMove(this);
	}
	
	public List<Tile> getValidMoves() {
		List<Tile> validMoves = new ArrayList<>();
		
		for (Tile tile : getPosition().getSurroundingTiles()) {
			if (canMove(tile)) validMoves.add(tile);
		}
		
		return validMoves;
	}
	
	@Override
	public void setPosition(Tile tile) {
		this.prevPosition = getPosition();
		super.setPosition(tile);
	}
}