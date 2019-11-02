package unsw.dungeon;

import java.util.*;

import javafx.animation.PauseTransition;
import javafx.util.Duration;

import java.util.ArrayList;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Movable {
	
	private Inventory inventory;
	private String name;
    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    
    public String getState() {
		return state;
	}

	public void Move(int xMove, int yMove) {
		Tile target = getAdjacentTile(xMove, yMove);
		if (target != null && target.canMove(this)) {
			moveMe(target);
			PauseTransition pauseTransition = new PauseTransition(Duration.millis(150));
			pauseTransition.setOnFinished(event -> getDungeon().playTurn());
			pauseTransition.play();
		}
	}
	@Override
	public void notifyComing(Movable mover) {
		if (mover instanceof Enemy) {
			Enemy enemy = (Enemy) mover;
			enemy.notifyComing(this);
		}
	}

	public void dies() {
    	getDungeon().endGame(false);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public boolean isInvincible() {
		if (inventory.getInvincibleTime() > 0) {
			return true;
		}
		return false;
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
	
	public boolean hasSword() {
		if (inventory.SwordHit() > 0) {
			return true;
		}
		return false;
	}
	
	public void useSword() {
		inventory.useSword();
	}
	
	public Sword getSword() {
		return inventory.getSword();
	}
	
	public Key keyHeld() {
		return inventory.getKey();
	}
	
	public void pickKey(Key k) {
		inventory.setKey(k);
	}
	
	public void useKey() {
		inventory.setKey(null);
	}
	
	public void pickSword(Sword s) {
		inventory.setSword(s);
	}
	
	public void pickPotion(InvincibilityPotion p) {
		inventory.pickPotion(20);
	}
	
	public void pickTreasure(Treasure t) {
		inventory.addTreasure(t);
	}
}
