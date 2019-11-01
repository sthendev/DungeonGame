package unsw.dungeon;

import java.util.*;

public class Door extends Entity {
	
	private String name;
	private Key key;
	private String state;
	
	public Door(Dungeon d, int x, int y, String name, Key key) {
		super(d, x, y);
		this.name = name;
		this.key = key;
		this.state = "untriggered";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public boolean isRight(Key k) {
		if (k.getDoor().equals(this)) {
			return true;
		}
		return false;
	}
	
	@Override
	public void handleInteraction(Entity e) {
		if (e instanceof Player && state.equals("untriggered")) {
			Player p = (Player) e;
			if (isRight(p.keyHeld())) {
				setState("triggered");
				p.useKey();
				notifyObservers();
			}
		}
	}
	
}
