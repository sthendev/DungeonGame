package unsw.dungeon;

public class Sword extends Entity {
	
	private int hits;
	
	public Sword(Tile position) {
		super(position);
		this.hits = 5;
	}
	
	@Override
	public void notifyComing(Movable e) {
		if (e instanceof Player) {
			Player p = (Player) e;
			Sword s = p.getSword();
			if (s != null) {
				p.dropItem(s, getCurrentTile());
			}
			p.pickItem(this);
			getCurrentTile().removeEntity(this);
		}
	}

	public int getHits() {
		return hits;
	}
	
	public void useHit() {
		this.hits--;
	}
	
	
}
