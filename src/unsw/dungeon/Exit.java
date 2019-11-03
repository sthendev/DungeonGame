package unsw.dungeon;

public class Exit extends Entity {
	
	private boolean playerHere;
	
	public Exit(Tile position) {
        super(position);
        this.playerHere = false;
    }
	
	public boolean playerIsHere() {
		return playerHere;
	}

	@Override
	public void notifyComing(Movable e) {
		if (e instanceof Player) {
			this.playerHere = true;
			notifyObservers();
		}
	}
	
	@Override
	public void notifyLeaving(Movable mover) {
		if (mover instanceof Player) {
			this.playerHere = false;
			notifyObservers();
		}
	}

	@Override
	public boolean isBlocking(Movable mover) {
		if (mover instanceof Enemy) return true;
		return false;
	}
	
}
