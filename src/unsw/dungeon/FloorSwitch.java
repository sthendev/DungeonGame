package unsw.dungeon;

public class FloorSwitch extends Entity {
	
	private boolean triggered;
	
	public FloorSwitch(Tile position) {
		super(position);
		this.triggered = false;
	}
	
	/**
	 * 
	 * @return whether or not this switch is currently triggered
	 */
	public boolean isTriggered() {
		return triggered;
	}
	
	@Override
	public void notifyComing(Movable mover) {
		if (mover instanceof Boulder) {
			this.triggered = true;
			SoundPlayer sd = new SoundPlayer();
			sd.playSound("switch.wav");
			notifyObservers();
		}
	}
	
	@Override
	public void notifyLeaving(Movable mover) {
		if (mover instanceof Boulder) {
			this.triggered = false;
			SoundPlayer sd = new SoundPlayer();
			sd.playSound("switch2.wav");
			notifyObservers();
		}
	}

}
