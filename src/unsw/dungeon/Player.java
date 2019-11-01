package unsw.dungeon;

import java.util.List;

import javafx.animation.PauseTransition;
import javafx.util.Duration;

import java.util.ArrayList;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Movable {
	
	private List<Entity> inventory;
	
    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, Tile position) {
        super(dungeon, position);
        this.inventory = new ArrayList<>();
    }
    
    public void addItem(Entity item) {
    	inventory.add(item);
    }
    
    public void removeItem(Entity item) {
    	inventory.remove(item);
    }

    public void moveUp() {
        attemptMove(getPosition().up());
    }

    public void moveDown() {
        attemptMove(getPosition().down());
    }

    public void moveLeft() {
        attemptMove(getPosition().left());
    }

    public void moveRight() {
        attemptMove(getPosition().right());
    }
    
    public void stay() {
        getDungeon().playTurn();
    }
    
    public void dropKey() {
    	
    }
    
    public void attemptMove(Tile target) {
    	if (target != null && target.canMove(this)) {
        	moveMe(target);
        	PauseTransition pauseTransition = new PauseTransition(Duration.millis(150));
        	pauseTransition.setOnFinished(event -> getDungeon().playTurn());
        	pauseTransition.play();
        }
    }
    
    public boolean isInvincible() {
    	for (Entity item : inventory) {
    		if (item instanceof InvincibilityPotion) return true;
    	}
    	return false;
    }
    
    public boolean hasSword() {
    	for (Entity item : inventory) {
    		if (item instanceof Sword) return true;
    	}
    	return false;
    }
    
    public void attack() {
    	for (Entity item : inventory) {
    		if (item instanceof Sword) {
    			Sword sword = (Sword) item;
    			sword.hit();
    			if (sword.getHits() == 0) inventory.remove(sword);
    			break;
    		}
    	}
    	notifyObservers();
    }
    
    public void newTurn() {
    	for (Entity item : inventory) {
    		if (item instanceof InvincibilityPotion) {
    			InvincibilityPotion potion = (InvincibilityPotion) item;
    			potion.moved();
    			if (potion.getMoves() == 0) inventory.remove(potion);
    			break;
    		}
    	}
    }
    
    public void dies() {
    	getDungeon().endGame();
    }

	@Override
	public void meet(Movable mover) {
		if (mover instanceof Enemy) {
			System.out.println("meet enemy");
			Enemy enemy = (Enemy) mover;
			if (isInvincible()) {
				enemy.dies();
			} else {
				dies();
			}
		}
	}
}
