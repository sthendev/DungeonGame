package unsw.dungeon;
import java.util.*;

public class Switch extends Entity {

	private int index;
	private State triggered;
	private State untriggered;
	private State state;
	
	public Switch(Tile tile, int index, State state) {
		super(tile);
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
		Movable m = null;
		List<Entity> en = this.entityOverlapped();
		for (Entity e : en) {
			if (e instanceof Player || e instanceof Boulder) {
				tri = 1;
				m = (Movable) e;
				break;
			}
		}
		if (tri == 1) {
			notifyComing(m);
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
