package unsw.dungeon;

public class Treasure extends Entity {
	
	private boolean collected = false;

	public Treasure(Tile tile) {
		super(tile);
	}
	
	@Override
	public void notifyComing(Movable e) {
		if (e instanceof Player) {
			Player p = (Player) e;
			p.pickItem(this);
			this.collected = true;
			getCurrentTile().removeEntity(this);
		}
	}
	
	public boolean isCollected() {
		return collected;
	}
	
}
