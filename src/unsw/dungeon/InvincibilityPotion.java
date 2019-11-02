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
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	@Override
	public void notifyComing(Movable e) {
		if (e instanceof Player) {
			Player p = (Player) e;
			p.pickPotion(this);
			setPlayer(p);
			getCurrentTile().removeEntity(this);
		}
	}
	
}
