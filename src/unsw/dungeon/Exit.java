package unsw.dungeon;
import java.util.*;

public class Exit extends Entity {
	
	public String state;

	public Exit(Dungeon d, int x, int y) {
		super(d, x, y);
		this.state = "untriggered";
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
		notifyObservers();
	}
	
	@Override
	public void handleInteraction(Entity e) {
		if (e instanceof Player && getDungeon().checkGoal()) {
			setState("triggered"); 
		}
	}
}
