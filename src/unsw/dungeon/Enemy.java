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
    	if (getDungeon().getPlayer().getX() != getX()
    		&& getDungeon().getPlayer().getY() != getY())
    		return false;
    	
    	List<Tile> tilesBetween = getDungeon().getTilesToPlayer(this);
    	for (Tile tile : tilesBetween) {
    		if (!tile.canMove(this)) return false;
    	}
    	
    	return true;
    }
    
    public void move() {
    	if (!alarmed && playerInSight()) this.alarmed = true;
    	if (!alarmed) return;
    	
    	List<Tile> validMoves = getValidMoves();
    	Tile bestMove = getCurrentTile();
    	
    	for (Tile tile : validMoves) {
    		if (getDungeon().distToPlayer(tile) < getDungeon().distToPlayer(bestMove)) {
    			bestMove = tile;
    		}
    	}
    	
    	if (!bestMove.equals(getCurrentTile())) moveMe(bestMove);
    }
    
    public void dies() {
    	if (getCurrentTile() != null) getCurrentTile().movedOff(this);
    	getDungeon().removeEnemy(this);
    	ceaseExistence();
    }
    
    @Override
    public boolean isBlocking(Movable mover) {
    	if (mover instanceof Enemy) return true;
    	return false;
    }

	@Override
	public void notifyComing(Movable mover) {
		if (mover instanceof Player) {
			Player player = (Player) mover;
			if (player.isInvincible()) {
				dies();
			} else if (player.hasSword()) {
				player.useSword();
				dies();
			} else {
				player.dies();
			}
		}
	}
	
}
