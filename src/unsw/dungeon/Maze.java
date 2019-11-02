package unsw.dungeon;

import java.util.*;

public class Maze implements Goal {

	private String name = "maze";
	private Dungeon dungeon;

	public Maze (Dungeon d) {
		super();
		this.dungeon = d;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean accomplished() {
		int x = dungeon.playerXPos();
		int y = dungeon.playerYPos();
		for (Entity e : dungeon.getTile(x, y)) {
			if (e instanceof Exit) {
				return true;
			}
		}
		return false;
	}
}
