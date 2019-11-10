package unsw.dungeon;

public abstract class TurnBasedPotion extends Entity {
	
	private int moves;

	public TurnBasedPotion(Tile position) {
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