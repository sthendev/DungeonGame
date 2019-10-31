/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import java.awt.geom.Point2D;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

    private int width, height;
    private Tile[][] board;
    private Player player;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.player = null;
        initializeBoard(width, height);
    }
    
    private void initializeBoard(int width, int height) {
    	this.board = new Tile[height][width];
    	for (int row = 0; row < height; row ++) {
    		for (int col = 0; col < width; col++) {
    			board[row][col] = new Tile(this, col, row);
    		}
    	}
    }
    
    public int getWidth() {
    	return width;
    }
    
    public int getHeight() {
    	return height;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addEntity(Entity entity) {
    	linkEntityTile(entity, board[entity.getY()][entity.getX()]);
    }
    
    public void moveEntity(Entity entity, Tile tile) {
    	entity.getPosition().removeEntity(entity);
    	linkEntityTile(entity, tile);
    }
    
    public boolean canMove(Entity entity, Tile tile) {
    	return true;
    }
    
    private void linkEntityTile(Entity entity, Tile tile) {
    	tile.placeEntity(entity);
    	entity.setPosition(tile);
    }
    
    public Tile getTile(int x, int y) {
    	if (x < 0 || x >= width
    		|| y < 0 || y >= height) 
    		return null;
    	return board[y][x];
    }

}
