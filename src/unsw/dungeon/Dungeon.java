/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import java.awt.geom.Point2D;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

    private int width, height;
    private List<Entity> entities;
    private Player player;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }
    
    public void postMove() {
    	for (Entity entity: entities) {
    		if (entity instanceof Enemy) {
    			Enemy enemy = (Enemy) entity;
    			enemy.move();
    		}
    	}
    }
    
    public double getDistToPlayer(Entity thing) {
    	return Point2D.distance((double) thing.getX(), (double) thing.getY(), (double) player.getX(), (double) player.getY());
    }
}
