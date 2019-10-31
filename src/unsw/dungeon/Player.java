package unsw.dungeon;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Movable {
    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, Tile position) {
        super(dungeon, position);
    }

    public void moveUp() {
    	Tile target = getPosition().up();
        attemptMove(target);
    }

    public void moveDown() {
        Tile target = getPosition().down();
        attemptMove(target);
    }

    public void moveLeft() {
        Tile target = getPosition().left();
        attemptMove(target);
    }

    public void moveRight() {
        Tile target = getPosition().right();
        attemptMove(target);
    }
    
    public void attemptMove(Tile target) {
    	if (target != null && dungeon.canMove(this, target)) {
        	move(target);
        }
    }
}
