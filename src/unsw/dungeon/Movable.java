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
	
	public Tile getPreviousTile() {
		return prevPosition;
	}
	
	public boolean getStillExists() {
		return stillExists;
	}
	
	public void ceaseExistence() {
		this.stillExists = false;
    	notifyObservers();
	}
    
	public void moveMe(Tile tile) {
		dungeon.moveEntity(this, tile);
	}
	
	public boolean canMove(Tile tile) {
		return tile.canMove(this);
	}
	
	public List<Tile> getValidMoves() {
		List<Tile> validMoves = new ArrayList<>();
		for (Tile tile : getCurrentTile().getSurroundingTiles()) {
			if (canMove(tile)) validMoves.add(tile);
		}
		return validMoves;
	}
	
	public List<Tile> getValidMovesFromTile(Tile tile) {
		List<Tile> validMoves = new ArrayList<>();
		for (Tile t : tile.getSurroundingTiles()) {
			if (canMove(t)) validMoves.add(t);
		}
		return validMoves;
	}
	
	@Override
	public void setCurrentTile(Tile tile) {
		this.prevPosition = getCurrentTile();
		super.setCurrentTile(tile);
	}
}
