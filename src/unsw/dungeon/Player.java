package unsw.dungeon;

import javafx.animation.PauseTransition;
import javafx.util.Duration;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Movable {
	
	private Inventory inventory;
    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */

	public Player(Dungeon dungeon, Tile position) {
		super(dungeon, position);
		this.inventory = new Inventory();
	}
	
	/**
	 * move player in direction of x and y offset
	 * @param xMove
	 * @param yMove
	 */
	public void Move(int xMove, int yMove) {
		Tile target = getAdjacentTile(xMove, yMove);
		if (target != null && target.canMove(this)) {
			newTurn();
			moveMe(target);
			PauseTransition pauseTransition = new PauseTransition(Duration.millis(150));
			pauseTransition.setOnFinished(event -> getDungeon().playTurn());
			pauseTransition.play();
		}
	}
    
	/**
	 * remain in current position but consume a turn
	 */
    public void stay() {
    	newTurn();
        getDungeon().playTurn();
    }

	@Override
	public void notifyComing(Movable mover) {
		if (mover instanceof Enemy) {
			Enemy enemy = (Enemy) mover;
			if (isInvincible()) {
				enemy.dies();
			} else if (hasSword()) {
				useTool();
				enemy.dies();
			} else {
				dies();
			}

		}
	}
	
	/**
	 * handle death of player
	 */
	public void dies() {
		setCurrentTile(null);
    	getDungeon().endGame(false);
	}
	
	/**
	 * 
	 * @return inventory of player
	 */
	public Inventory getInventory() {
		return inventory;
	}
	
	/**
	 * handle adding item to player inventory from tile
	 * @param item
	 */
	public void pickItem(Entity item) {
		inventory.addItem(item);
		if (item instanceof Key) {
			getDungeon().highlightDoor((Key) item);
		}
	}
	
	/**
	 * handle dropping item from inventory to tile
	 * @param item
	 * @param tile
	 */
	public void dropItem(Entity item, Tile tile) {
		inventory.removeItem(item);
		tile.placeEntity(item);
	}
	
	/**
	 * 
	 * @return whether or not player is invincible
	 */
	public boolean isInvincible() {
		if (inventory.getInvincibilityPotion() != null) return true;
		return false;
	}
	
	/**
	 * 
	 * @return whether or not player is using freeze ability
	 */
	public boolean isFreezing() {
		if (inventory.getFreezePotion() != null) return true;
		return false;
	}
	
	/**
	 * 
	 * @return whether or not player is in ghost state
	 */
	public boolean isGhost() {
		if (inventory.getGhostPotion() != null) return true;
		return false;
	}
	
	/**
	 * process interactions of player using up a turn
	 */
	public void newTurn() {
		inventory.usePotions();
		notifyObservers();
	}
	
	/**
	 * 
	 * @return whether or not player has sword
	 */
	public boolean hasSword() {
		for (Entity e : inventory.getItems()) {
			if (e instanceof Sword) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @return whether or not player has hammer
	 */
	public boolean hasHammer() {
		for (Entity e : inventory.getItems()) {
			if (e instanceof Hammer) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * use currently equipped tool
	 */
	public void useTool() {
		inventory.useTool();
		notifyObservers();
	}
	
	/**
	 * 
	 * @return tool held by player
	 */
	public HitBasedTool toolHeld() {
		return inventory.getTool();
	}
	
	/**
	 * 
	 * @return key held by player
	 */
	public Key keyHeld() {
		return inventory.getKey();
	}
	
	/**
	 * consume key to open door
	 */
	public void useKey() {
		inventory.removeItem(keyHeld());
		getDungeon().highlightDoor(null);
	}
}
