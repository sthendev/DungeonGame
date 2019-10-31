package unsw.dungeon;

public class Exit extends Entity {
	public Exit(Tile position) {
        super(position);
    }
	
	@Override
	public boolean isBlocking(Entity entity) {
		if (entity instanceof Enemy) return true;
		return false;
	}
}
