package unsw.dungeon;

import java.util.List;
import java.util.ArrayList;

/**
 * An entity in the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public abstract class Entity implements Subject {

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
		for (Observer observer : observers) {
			observer.update(this);
		}
	}

    public int getX() {
        return position.getX();
    }
  
    public int getY() {
        return position.getY();
    }
    
    public Tile getPosition() {
    	return position;
    }
    
    public void setPosition(Tile tile) {
    	this.position = tile;
    	notifyObservers();
    }
    
    public boolean isBlocking(Movable mover) {
    	return false;
    };
    
    public void meet(Movable mover) {
    	return;
    };
    
    public void leave(Movable mover) {
    	return;
    };
}
