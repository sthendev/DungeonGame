package unsw.dungeon;

import java.util.List;
import java.util.ArrayList;

public class Enemy extends Entity {

    private Dungeon dungeon;

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Enemy(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
    }
    
    public void move() {
    	Entity best = null;
    	List<Entity> directions = getValidDirections();
    	for (Entity direction: directions) {
    		if (best == null) {
    			best = direction;
    			continue;
    		}
    		
    		if (dungeon.getDistToPlayer(direction) < dungeon.getDistToPlayer(best)) {
    			best = direction;
    		}
    	}
    	
    	if (best != null) {
    		x().set(best.getX());
    		y().set(best.getY());
    	}
    }
    
    public List<Entity> getValidDirections() {
    	 List<Entity> directions = new ArrayList<>();
    	 if (getY() > 0) directions.add(new Entity(getX(), getY() - 1));
    	 if (getY() < dungeon.getHeight() - 1) directions.add(new Entity(getX(), getY() + 1));
    	 if (getX() > 0) directions.add(new Entity(getX() - 1, getY()));
    	 if (getX() < dungeon.getWidth() - 1) directions.add(new Entity(getX() + 1, getY()));
    	 
    	 return directions;
    }

    public void moveUp() {
        if (getY() > 0)
            y().set(getY() - 1);
    }

    public void moveDown() {
        if (getY() < dungeon.getHeight() - 1)
            y().set(getY() + 1);
    }

    public void moveLeft() {
        if (getX() > 0)
            x().set(getX() - 1);
    }

    public void moveRight() {
        if (getX() < dungeon.getWidth() - 1)
            x().set(getX() + 1);
    }
}
