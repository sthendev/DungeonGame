package unsw.dungeon;

public class Treasure extends Entity {
	public Treasure(Tile position) {
		super(position);
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
