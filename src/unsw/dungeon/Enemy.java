package unsw.dungeon;

import java.util.List;

public class Enemy extends Movable {
	
	private boolean alarmed;

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Enemy(Dungeon dungeon, Tile position) {
        super(dungeon, position);
        this.alarmed = false;
    }
    
    public boolean playerInSight() {
    	if (dungeon.getPlayer().getX() != getX()
    		&& dungeon.getPlayer().getY() != getY())
    		return false;
    	
    	List<Tile> tilesBetween = dungeon.getTilesToPlayer(this);
    	for (Tile tile : tilesBetween) {
    		if (!tile.canMove(this)) return false;
    	}
    	
    	return true;
    	
    }
    
    public void move() {
    	if (!alarmed && playerInSight()) this.alarmed = true;
    	if (!alarmed) return;
    	
    	List<Tile> validMoves = getValidMoves();
    	Tile bestMove = getPosition();
    	
    	for (Tile tile : validMoves) {
    		if (bestMove == null) {
    			bestMove = tile;
    			continue;
    		}
    		if (dungeon.distToPlayer(tile) < dungeon.distToPlayer(bestMove)) {
    			bestMove = tile;
    		}
    	}
    	
    	moveMe(bestMove);
    }
    
    @Override
    public boolean isBlocking(Entity entity) {
    	if (entity instanceof Enemy) return true;
    	return false;
    }
}
