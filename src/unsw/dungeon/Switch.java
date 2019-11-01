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
	public void handleInteraction(Entity e) {
		if (e instanceof Player || e instanceof Boulder) {
			Player p = (Player) e;
			if (p.keyHeld() != null) {
				Key k = p.keyHeld();
				int x = p.getX();
				int y = p.getY();
				k.setX(x);
				k.setY(y);
				getDungeon().addEntity(k, x, y);
			}
			p.pickKey(this);
			setPlayer(p);
			getDungeon().removeEntity(this, getX(), getY());
			notifyObservers();
			// How does javaFX know the key has swapped?
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
	}
	
}
