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

    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.
    private IntegerProperty x, y;
    private List<Observer> observers;
    private Dungeon dungeon;

    /**
     * Create an entity positioned in square (x,y)
     * @param x
     * @param y
     */
    public Entity(Dungeon d, int x, int y) {
    	super();
    	this.dungeon = d;
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
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
		for (Observer o : getObservers()) {
			o.update(this);
		}
	}
	
    public List<Observer> getObservers() {
		return observers;
	}

	public void setObservers(List<Observer> observers) {
		this.observers = observers;
	}

	public IntegerProperty x() {
        return x;
    }

    public IntegerProperty y() {
        return y;
    }

    public void setX(int x) {
		this.x.set(x);
	}

	public void setY(int y) {
		this.y.set(y);
	}

	public int getY() {
        return y().get();
    }

    public int getX() {
        return x().get();
    }
    
    public Dungeon getDungeon() {
		return dungeon;
	}

	public void setDungeon(Dungeon dungeon) {
		this.dungeon = dungeon;
	}

	abstract public void handleInteraction(Entity e);
    
    public boolean isBlocking(Entity entity) {
    	return false;
    }

}
