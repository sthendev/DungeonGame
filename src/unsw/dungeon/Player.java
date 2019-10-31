package unsw.dungeon;

import java.util.*;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity implements Subject {

    private Dungeon dungeon;
    private String name;
    private ArrayList<Observer> observers;
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
        super(x, y);
        this.dungeon = dungeon;
        this.name = name;
        this.observers = new ArrayList<Observer>();
        this.lastXMove = this.lastYMove = 0;
        this.state = "alive";
        this.inventory = inv;
    }
    
    public void move(int xMove, int yMove) {
    	if (getY() > 0 && yMove > 0 || yMove < 0 && getY() < dungeon.getHeight() - 1) {
    		y().set(getY() + yMove);
    		setLastYMove(yMove);
    		setLastXMove(0);
    	} else if (xMove > 0 && getX() < 0 || xMove < 0 && getX() < dungeon.getWidth() - 1) {
    		x().set(getX() + xMove);
    		setLastYMove(0);
    		setLastXMove(xMove);
    	}
    	notifyObserver();
    }
    
    public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
		notifyObserver();
	}

	@Override
    public void addObserver(Observer o) {
    	observers.add(o);
    }
    
    @Override
	public void removeObserver(Observer o) {
		observers.remove(o);
	}
	
	@Override
	public void notifyObserver() {
		for (Observer o : observers) {
			o.update(this);
		}
	}

	public Dungeon getDungeon() {
		return dungeon;
	}

	public void setDungeon(Dungeon dungeon) {
		this.dungeon = dungeon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Observer> getObservers() {
		return observers;
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
	
	@Override
	public void handleInteraction(Entity e) {
		
	}
}
