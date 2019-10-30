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
    	 
    	 if (dungeon.canMove(this, up())) directions.add(up());
    	 if (dungeon.canMove(this, down())) directions.add(down());
    	 if (dungeon.canMove(this, left())) directions.add(left());
    	 if (dungeon.canMove(this, right())) directions.add(right());
    	 if (dungeon.canMove(this, stay())) directions.add(stay());
    	 
    	 return directions;
    }

    public Entity up() {
        return new Entity(getX(), getY() - 1);
    }

    public Entity down() {
    	return new Entity(getX(), getY() + 1);
    }

    public Entity left() {
    	return new Entity(getX() - 1, getY());
    }

    public Entity right() {
    	return new Entity(getX() + 1, getY());
    }
    
    public Entity stay() {
    	return new Entity(getX(), getY());
    }
}
