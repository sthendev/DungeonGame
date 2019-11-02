package unsw.dungeon;

public class Switch extends Entity {

	private int index;
	private State triggered;
	private State untriggered;
	private State state;
	
	public Switch(Dungeon d, int x, int y, int index, State state) {
		super(d, x, y);
		this.index = index;
		this.triggered = new Triggered(this);
		this.untriggered = new Untriggered(this);
		this.state = state;
	}
	
	@Override
	public void notifyComing(Movable e) {
		state.toTrigger();
	}
	
	public void checkState() {
		int tri = 0;
		Tile t = getDungeon().getTile(getX(), getY());
		for (Entity e : t) {
			if (e instanceof Player || e instanceof Boulder) {
				tri = 1;
				break;
			}
		}
		if (tri == 1) {
			handleInteraction(this);
		} else {
			state.toUntrigger();
		}
	}
	
	public int getIndex() {
		return index;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}
	
	public State getTriggered() {
		return triggered;
	}

	public State getUntriggered() {
		return untriggered;
	}

	public State getState() {
		return state;
	}
	
	public void setState(State state) {
		this.state = state;
		notifyObservers();
	}
	
}
