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

	public void dies() {
		setCurrentTile(null);
    	getDungeon().endGame(false);
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
	public void pickItem(Entity item) {
		inventory.addItem(item);
		if (item instanceof Key) {
			getDungeon().highlightDoor((Key) item);
		}
	}
	
	public void dropItem(Entity item, Tile tile) {
		inventory.removeItem(item);
		tile.placeEntity(item);
	}

	public boolean isInvincible() {
		if (inventory.getInvincibilityPotion() != null) return true;
		return false;
	}
	
	public boolean isFreezing() {
		if (inventory.getFreezePotion() != null) return true;
		return false;
	}
	
	public boolean isGhost() {
		if (inventory.getGhostPotion() != null) return true;
		return false;
	}

	public void newTurn() {
		inventory.usePotions();
		notifyObservers();
	}
	
	public boolean hasSword() {
		if (toolHeld() instanceof Sword) {
			return true;
		}
		return false;
	}
	
	public boolean hasHammer() {
		if (toolHeld() instanceof Hammer) {
			return true;
		}
		return false;
	}
	
	public void useTool() {
		inventory.useTool();
		notifyObservers();
	}
	
	public HitBasedTool toolHeld() {
		return inventory.getTool();
	}
	
	public Key keyHeld() {
		return inventory.getKey();
	}
	
	public void useKey() {
		inventory.removeItem(keyHeld());
		getDungeon().highlightDoor(null);
	}
}
