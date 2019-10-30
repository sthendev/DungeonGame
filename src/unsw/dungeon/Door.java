package unsw.dungeon;

import java.util.*;

public class Door extends Entity {

	private String name;
	private Key key;
	private String state;
	
	public Door(int x, int y, String name, Key key) {
		super(x, y);
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
	
	public void toTrigger(Key k) {
		if (state.equals("untriggered")) {
			if (k.getDoor().equals(this)) {
				state = "triggered";
			}
		}
	}
	
	
}
