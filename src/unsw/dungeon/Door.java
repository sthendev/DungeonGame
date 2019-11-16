package unsw.dungeon;

public class Door extends Entity {
	
	private int id;
	private boolean opened;
	private boolean keyHeld;
	private Dungeon dungeon;
	
	public Door(int id, Tile position, Dungeon dungeon) {
		super(position);
		this.id = id;
		this.opened = false;
		this.keyHeld = false;
		this.dungeon = dungeon;
	}
	
	public boolean isOpened() {
		return opened;
	}
	
	public void destroy() {
		getCurrentTile().removeEntity(this);
		dungeon.removeDoor(this);
		SoundPlayer sd = new SoundPlayer();
		sd.playSound("break_wall.wav");
	}
	
	public boolean playerHasKey() {
		return keyHeld;
	}
	
	public void setKeyHeld(boolean keyHeld) {
		this.keyHeld = keyHeld;
		notifyObservers();
	}
	
	@Override
	public boolean isBlocking(Movable mover) {
		if (opened) return false;
		if (mover instanceof Player) {
			Player player = (Player) mover;
			if (isRight(player.keyHeld())) return false;
			else if (player.isGhost()) {
				Tile oppositeTile = getCurrentTile().getOppositeTile(player.getCurrentTile());
				if (oppositeTile.hasWall() || oppositeTile.hasClosedDoor()) return true;
				if (player.canMove(oppositeTile)) return false;
			} else if (player.hasHammer()) return false;
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
			Tile oppositeTile = getCurrentTile().getOppositeTile(p.getPreviousTile());
			if (p.isGhost() && !oppositeTile.hasWall() && !oppositeTile.hasClosedDoor()) {
				oppositeTile.movedOn(p);
			} else if (isRight(p.keyHeld())) {
				opened = true;
				p.useKey();
		    	SoundPlayer sd = new SoundPlayer();
		    	sd.playSound("creak.wav");
				notifyObservers();
			} else if (p.hasHammer()) {
				p.useTool();
				destroy();
			}
		}
	}
	
	@Override
	public boolean isTransparent() {
		return isOpened();
	}
	
}
