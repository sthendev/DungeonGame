package unsw.dungeon;

public class Exit extends Entity {
	public Exit(Tile position) {
        super(position);
    }

	@Override
	public boolean isBlocking(Movable mover) {
		if (mover instanceof Enemy) return true;
		return false;
	}
}
