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
    	
    }
    
    public List<Entity> getValidDirections() {
    	 
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
