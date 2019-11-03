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
			if (p.getSword() != null) {
				Sword s = p.getSword();
				s.setCurrentTile(p.getCurrentTile());
				getCurrentTile().placeEntity(s);
			}
			p.pickSword(this);
			getCurrentTile().removeEntity(this);
		}
	}

	public int getHits() {
		return hits;
	}
	
	public void useHits() {
		this.hits--;
	}
	
	
}
