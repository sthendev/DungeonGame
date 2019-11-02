package unsw.dungeon;

public class Boulder extends Entity {
	
	public Boulder(Dungeon dungeon, Tile position) {
		super(dungeon, position);
	}
	
	@Override 
	public boolean isBlocking(Movable mover) {
		if (mover instanceof Boulder) {
			return true;
		} else if (mover instanceof Player) {
			Direction playerDirection = getPosition().getDirectionOfTile(mover.getPosition());
			if (playerDirection == Direction.UP && canMove(getPosition().down())) return false;
			else if (playerDirection == Direction.DOWN && canMove(getPosition().up())) return false;
			else if (playerDirection == Direction.LEFT && canMove(getPosition().right())) return false;
			else if (playerDirection == Direction.RIGHT && canMove(getPosition().left())) return false;
		}
		return true;
	}
	
	@Override
	public void notifyComing(Movable mover) {
		if (mover instanceof Player) {
			Direction playerDirection = getPosition().getDirectionOfTile(mover.getPrevPosition());
			if (playerDirection == Direction.UP) moveMe(getPosition().down());
			else if (playerDirection == Direction.DOWN) moveMe(getPosition().up());
			else if (playerDirection == Direction.LEFT) moveMe(getPosition().right());
			else if (playerDirection == Direction.RIGHT) moveMe(getPosition().left());
		}
	}


	
}
