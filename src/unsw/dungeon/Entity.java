package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import java.util.*;
import javafx.beans.property.SimpleIntegerProperty;

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
	
    public List<Observer> getObservers() {
		return observers;
	}

    public int getX() {
        return position.getX();
    }
  
    public int getY() {
        return position.getY();
    }
    
    public Tile getCurrentTile() {
    	return position;
    }
    
    public void setCurrentTile(Tile tile) {
    	this.position = tile;
    	notifyObservers();
    }
    
    public List<Entity> entityOverlapped() {
    	return position.getEntities();
    }
    
    public Tile getAdjacentTile(int x, int y) {
		return position.getAdjacentTile(x, y);
    }
    
    //Default behaviour is not blocking
    public boolean isBlocking(Movable mover) {
    	return false;
    };
    
    //Default not blocking line of sight of enemies
    public boolean isTransparent() {
    	return true;
    }

    //Default behaviour is not notifying
    public void notifyLeaving(Movable mover) {
    	
    };

	abstract public void notifyComing(Movable mover);
}
