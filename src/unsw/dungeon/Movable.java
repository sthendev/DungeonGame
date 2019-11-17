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
	
	/**
	 * 
	 * @return dungeon that Movable entity is a part of
	 */
	public Dungeon getDungeon() {
		return dungeon;
	}
	
	/**
	 * 
	 * @return the Tile that Movable entity was previously on
	 */
	public Tile getPreviousTile() {
		return prevPosition;
	}
	
	/**
	 * 
	 * @return whether or not the Movable entity was destroyed in the process of movement
	 */
	public boolean getStillExists() {
		return stillExists;
	}
	
	/**
	 * set existence status of Movable entity
	 */
	public void ceaseExistence() {
		this.stillExists = false;
    	notifyObservers();
	}
    
	/**
	 * process movement of Movable entity onto target Tile
	 * @param tile
	 */
	public void moveMe(Tile tile) {
		dungeon.moveEntity(this, tile);
	}
	
	/**
	 * 
	 * @param tile
	 * @return whether or not Movable entity can move to target Tile
	 */
	public boolean canMove(Tile tile) {
		return tile.canMove(this);
	}
	
	/**
	 * 
	 * @return Tiles that Movable entity can move to
	 */
	public List<Tile> getValidMoves() {
		List<Tile> validMoves = new ArrayList<>();
		for (Tile tile : getCurrentTile().getSurroundingTiles()) {
			if (canMove(tile)) validMoves.add(tile);
		}
		return validMoves;
	}
	
	/**
	 * 
	 * @param tile
	 * @return Tiles that Movable entity can move to FROM specified source Tile
	 */
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
