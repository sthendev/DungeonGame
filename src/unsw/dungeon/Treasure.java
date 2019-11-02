package unsw.dungeon;

public class Treasure extends Entity {
	
	private boolean collected = false;

	public Treasure(Tile t) {
		super(t);
	}
	
	@Override
	public void notifyComing(Movable e) {
		if (e instanceof Player) {
			Player p = (Player) e;
			p.pickTreasure(this);
			this.collected = true;
			getCurrentTile().removeEntity(this);
		}
	}
	
	public boolean isCollected() {
		return collected;
	}
	
}
