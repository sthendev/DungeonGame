package unsw.dungeon;

public class Switch extends Entity {

	private int index;
	private State triggered;
	private State untriggered;
	
	private State state;
	
	public Switch(int x, int y, int index, State state) {
		super(x, y);
		this.index = index;
		this.triggered = new Triggered(this);
		this.untriggered = new Untriggered(this);
		this.state = state;
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
	}
	
	
}
