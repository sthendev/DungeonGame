package unsw.dungeon;
import java.util.*;

public class Exit extends Entity {
	
	private boolean state;
	private Dungeon dungeon;

	public Exit(Dungeon d, Tile position) {
        super(position);
		this.state = false;
		this.dungeon = d;
    }

	public boolean getState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
		notifyObservers();
	}
	
	public Dungeon getDungeon() {
		return dungeon;
	}

	public void setDungeon(Dungeon dungeon) {
		this.dungeon = dungeon;
	}

	@Override
	public void notifyComing(Movable e) {
		if (e instanceof Player && dungeon.checkGoal()) {
			setState(true); 
		}
	}
	
	@Override
	public boolean isBlocking(Movable mover) {
		if (mover instanceof Enemy) return true;
		return false;
	}
	
}
