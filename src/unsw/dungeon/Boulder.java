package unsw.dungeon;

public class Boulder extends Movable {
	
	public Boulder(Dungeon dungeon, Tile position) {
		super(dungeon, position);
	}
	
	@Override 
	public boolean isBlocking(Movable mover) {
		if (mover instanceof Boulder) {
			return true;
		} else if (mover instanceof Player) {
			Direction playerDirection = getCurrentTile().getDirectionOfTile(mover.getCurrentTile());
			if (playerDirection == Direction.UP && canMove(getAdjacentTile(0, 1))) return false;
			else if (playerDirection == Direction.DOWN && canMove(getAdjacentTile(0, -1))) return false;
			else if (playerDirection == Direction.LEFT && canMove(getAdjacentTile(1, 0))) return false;
			else if (playerDirection == Direction.RIGHT && canMove(getAdjacentTile(-1, 0))) return false;
		}
		return true;
	}
	
	@Override
	public void notifyComing(Movable mover) {
		if (mover instanceof Player) {
			Direction playerDirection = getCurrentTile().getDirectionOfTile(mover.getPrevPosition());
			if (playerDirection == Direction.UP) moveMe(getAdjacentTile(0, -1));
			else if (playerDirection == Direction.DOWN) moveMe(getAdjacentTile(0, 1));
			else if (playerDirection == Direction.LEFT) moveMe(getAdjacentTile(1, 0));
			else if (playerDirection == Direction.RIGHT) moveMe(getAdjacentTile(-1, 0));
		}
	}


	
}
