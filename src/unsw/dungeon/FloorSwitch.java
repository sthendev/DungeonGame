package unsw.dungeon;

public class FloorSwitch extends Entity {
	
	private boolean triggered;
	
	public FloorSwitch(Tile position) {
		super(position);
		this.triggered = false;
	}
	
	public boolean isTriggered() {
		return triggered;
	}
	
	@Override
	public void meet(Movable mover) {
		if (mover instanceof Boulder) {
			this.triggered = true;
		}
	}
	
	@Override
	public void leave(Movable mover) {
		if (mover instanceof Boulder) {
			this.triggered = false;
		}
	}

}
