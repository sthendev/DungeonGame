package unsw.dungeon;

public class HitBasedTool extends Entity {
	private int hits;
	
	public HitBasedTool(Tile position, int hits) {
		super(position);
		this.hits = hits;
	}
	
	public int getHits() {
		return hits;
	}
	
	public void useHit() {
		this.hits--;
	}
	
	@Override
	public void notifyComing(Movable e) {
		if (e instanceof Player) {
			Player p = (Player) e;
			HitBasedTool t = p.toolHeld();
			if (t != null) {
				p.dropItem(t, getCurrentTile());
			}
			p.pickItem(this);
			getCurrentTile().removeEntity(this);
		}
	}
}
