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
			if (isRight(((Player) mover).keyHeld())) return false;
		}
		return true;
	}

	public boolean isRight(Key k) {
		if (k != null && k.getId() == id) {
			return true;
		}
		return false;
	}
	
	@Override
	public void notifyComing(Movable mover) {
		if (mover instanceof Player && opened == false) {
			Player p = (Player) mover;
			if (isRight(p.keyHeld())) {
				opened = true;
				p.useKey();
				notifyObservers();
			}
		}
	}
	
	@Override
	public boolean isTransparent() {
		return isOpened();
	}

	
}
