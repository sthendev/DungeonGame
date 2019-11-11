package unsw.dungeon;

public class Boulder extends Movable {
	
	public Boulder(Dungeon dungeon, Tile position) {
		super(dungeon, position);
	}
	
	@Override 
	public boolean isBlocking(Movable mover) {
		if (mover instanceof Player) {
			if (canMove(getCurrentTile().getOppositeTile(mover.getCurrentTile()))) return false;
		}
		return true;
	}
	
	@Override
	public void notifyComing(Movable mover) {
		if (mover instanceof Player) {
			moveMe(getCurrentTile().getOppositeTile(mover.getPreviousTile()));
		}
	}
	
	@Override
	public boolean isTransparent() {
		return false;
	}


	
}
