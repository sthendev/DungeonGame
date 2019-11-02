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
		return !opened;
	}

	public boolean isRight(Key k) {
		if (k.getId() == id) {
			return true;
		}
		return false;
	}
	
	@Override
	public void notifyComing(Movable e) {
		if (e instanceof Player && opened == false) {
			Player p = (Player) e;
			if (isRight(p.keyHeld())) {
				opened = true;
				p.useKey();
			}
		}
	}
	
}
