package unsw.dungeon;

import java.util.*;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {

    private String name;
    private int lastXMove;
    private int lastYMove;
    private String state;
    private Inventory inventory;

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, String name, int x, int y, Inventory inv) {
        super(dungeon, x, y);
        this.name = name;
        this.lastXMove = this.lastYMove = 0;
        this.state = "alive";
        this.inventory = inv;
    }
    
    public void move(int xMove, int yMove) {
    	if (getY() > 0 && yMove > 0 || yMove < 0 && getY() < getDungeon().getHeight() - 1) {
    		y().set(getY() + yMove);
    		setLastYMove(yMove);
    		setLastXMove(0);
    	} else if (xMove > 0 && getX() < 0 || xMove < 0 && getX() < getDungeon().getWidth() - 1) {
    		x().set(getX() + xMove);
    		setLastYMove(0);
    		setLastXMove(xMove);
    	}
    	notifyObservers();
    }
    
    public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
		notifyObservers();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getLastXMove() {
		return lastXMove;
	}

	public void setLastXMove(int lastXMove) {
		this.lastXMove = lastXMove;
	}

	public int getLastYMove() {
		return lastYMove;
	}

	public void setLastYMove(int lastYMove) {
		this.lastYMove = lastYMove;
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
	public boolean weaponed() {
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
	
	public void pickPotion(Potion p) {
		inventory.pickPotion(20);
	}
	
	public void pickTreasure(Treasure t) {
		inventory.addTreasure(t);
	}
	
	@Override
	public void handleInteraction(Entity e) {
		
	}
}
