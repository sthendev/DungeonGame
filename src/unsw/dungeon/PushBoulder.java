package unsw.dungeon;

import java.util.*;

public class PushBoulder implements Goal {
	
	private String name = "pushBoulder";
	private Dungeon dungeon;

	public PushBoulder (Dungeon d) {
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
		int boulder = 0;
		int floorSwitch = 0;
		for (ArrayList<ArrayList<Entity>> xloop: dungeon.getEntities()) {
			for (ArrayList<Entity> yloop : xloop) {
				for (Entity e : yloop) {
					if (e instanceof Boulder) {
						boulder++;
					} else if (e instanceof Switch) {
						floorSwitch++;
					}
				}
			}
		}
		if (boulder == floorSwitch) {
			return true;
		} else {
			return false;
		}
	}
}
