package unsw.dungeon;

public class InvincibilityPotion extends Entity {
	
	private int moves;
	
	public InvincibilityPotion(Tile position) {
		super(position);
		this.moves = 5;
	}
	
	public int getMoves() {
		return moves;
	}

	@Override
	public void notifyComing(Movable e) {
		if (e instanceof Player) {
			Player p = (Player) e;
			p.pickPotion(this);
			getCurrentTile().removeEntity(this);
		}
	}
	
}
