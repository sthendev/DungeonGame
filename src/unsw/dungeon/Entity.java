package unsw.dungeon;

import java.util.List;
import java.util.ArrayList;

/**
 * An entity in the dungeon.
 * @author Robert Clifton-Everest
 *
 */
abstract public class Entity implements Subject {

    private Tile position;
    private List<Observer> observers;

    /**
     * Create an entity positioned in square (x,y)
     * @param x
     * @param y
     */
    
    public Entity(Tile position) {
        this.position = position;
        this.observers = new ArrayList<>();
    }
    
    @Override
	public void addObserver(Observer obj) {
		observers.add(obj);
	}

	@Override
	public void removeObserver(Observer obj) {
		observers.remove(obj);
	}
	
	@Override
	public void notifyObservers() {
		List<Observer> observersCopy = new ArrayList<>(observers);
		for (Observer observer : observersCopy) {
			observer.update(this);
		}
	}
	
	/**
	 * 
	 * @return x position of entity
	 */
    public int getX() {
        return position.getX();
    }
    
    /**
     * 
     * @return y position of entity
     */
    public int getY() {
        return position.getY();
    }
    
    /**
     * 
     * @return current tile of entity
     */
    public Tile getCurrentTile() {
    	return position;
    }
    
    /**
     * set current tile of entity
     * @param tile
     */
    public void setCurrentTile(Tile tile) {
    	this.position = tile;
    	notifyObservers();
    }
    
    /**
     * 
     * @param x
     * @param y
     * @return adjacent tile with specified x and y offeset from current tile
     */
    public Tile getAdjacentTile(int x, int y) {
		return position.getAdjacentTile(x, y);
    }
    
    //Default behaviour is not blocking
    /**
     * 
     * @param mover
     * @return whether or not this entity will block Movable entity for moving onto current tile
     */
    public boolean isBlocking(Movable mover) {
    	return false;
    };
    
    //Default not blocking line of sight of enemies
    /**
     * 
     * @param mover
     * @return whether or not this entity is "see through" for enemies
     */
    public boolean isTransparent() {
    	return true;
    }

    //Default behaviour is not doing anything
    /**
     * handle interactions when Movable entity moves off from this entity
     * @param mover
     */
    public void notifyLeaving(Movable mover) {
    	
    };
    
    //Default behaviour is not doing anything
    /**
     * handle interactions when Movable entity moves on to this entity
     * @param mover
     */
	public void notifyComing(Movable mover) {
		
	}
}
