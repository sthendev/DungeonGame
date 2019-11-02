package unsw.dungeon;

public class Sword extends Entity {
	
	int hits;
	
	public Sword(Tile position) {
		super(position);
		this.hits = 5;
	}
	
	public int getHits() {
		return hits;
	}
	
	public void hit() {
		this.hits -= 1;
	}
	
	@Override
	public void meet(Movable mover) {
		if (mover instanceof Player) {
			Player player = (Player) mover;
			if (!player.hasSword()) {
				getPosition().removeEntity(this);
				player.addItem(this);
			}
		}
	}
}
