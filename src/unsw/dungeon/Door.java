package unsw.dungeon;

public class Door extends Entity {
	
	private int id;
	private boolean opened;
	
	public Door(int id, Tile position) {
		super(position);
		this.id = id;
		this.opened = false;
	}
	
	public boolean isOpened() {
		return opened;
	}
	
	@Override
	public boolean isBlocking(Movable mover) {
		if (opened) return false;
		if (mover instanceof Player) {
			Player player = (Player) mover;
			Key playerKey = player.getKey();
			if (playerKey != null && playerKey.getId() == id) {
				this.opened = true;
				player.removeItem(playerKey);
				notifyObservers();
				return false;
			}
		}
		return true;
	}
	
}
