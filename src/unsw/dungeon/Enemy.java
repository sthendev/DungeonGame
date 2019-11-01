package unsw.dungeon;
import java.util.*;

import unsw.dungeon.Observer;

public class Enemy extends Entity implements Subject {

	 private boolean alarmed;
	 private String state;
	 private Dungeon dungeon;

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
	 public Enemy(Dungeon d, Tile position) {
        super(d, position);
        this.state = "alive";
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
	public void handleInteraction(Entity e) {
		if (e instanceof Player) {
			Player p = (Player) e;
			if (p.isInvincible() || p.weaponed()) {
				setState("dead");
			} else {
				dungeon.endGame(false);
			}
		}
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
		notifyObservers();
	}
	
}
