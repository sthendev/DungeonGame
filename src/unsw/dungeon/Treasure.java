package unsw.dungeon;

public class Treasure extends Entity {
	
	private boolean collected;
	
	public Treasure(Tile position) {
		super(position);
		this.collected = false;
	}
	
	public boolean isCollected() {
		return collected;
	}
	
	@Override
	public void meet(Movable mover) {
		if (mover instanceof Player) {
			Player player = (Player) mover;
			getPosition().removeEntity(this);
			player.addItem(this);
			this.collected = true;
			notifyObservers();
		}
	}
}
