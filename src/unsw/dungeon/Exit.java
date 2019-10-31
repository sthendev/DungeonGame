package unsw.dungeon;
import java.util.*;

public class Exit extends Entity {
	
	public String state;

	public Exit(int x, int y) {
		super(x, y);
		this.state = "untriggered";
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public void handleInteraction() {
		
	}
}
