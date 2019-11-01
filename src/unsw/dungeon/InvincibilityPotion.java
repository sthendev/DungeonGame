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
	
	public void moved() {
		this.moves -= 1;
		System.out.println(moves);
	}
	
	@Override
	public void meet(Movable mover) {
		if (mover instanceof Player) {
			Player player = (Player) mover;
			getPosition().removeEntity(this);
			player.addItem(this);
		}
	}
	
}
