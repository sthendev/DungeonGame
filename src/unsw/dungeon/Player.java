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
        attemptMove(getPosition().up());
    }

    public void moveDown() {
        attemptMove(getPosition().down());
    }

    public void moveLeft() {
        attemptMove(getPosition().left());
    }

    public void moveRight() {
        attemptMove(getPosition().right());
    }
    
    public void attemptMove(Tile target) {
    	if (target != null && target.canMove(this)) {
        	moveMe(target);
        	dungeon.playTurn();
        }
    }
}
