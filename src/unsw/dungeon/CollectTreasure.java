package unsw.dungeon;
import java.util.*;

public class CollectTreasure implements Goal {
	
	private String name = "treasure";
	private Dungeon dungeon;

	public CollectTreasure(Dungeon d) {
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
		for (ArrayList<ArrayList<Entity>> xloop: dungeon.getEntities()) {
			for (ArrayList<Entity> yloop : xloop) {
				for (Entity e : yloop) {
					if (e instanceof Treasure) {
						return false;
					}
				}
			}
		}
		return true;
	}
}
