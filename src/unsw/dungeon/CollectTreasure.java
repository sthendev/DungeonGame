package unsw.dungeon;
import java.util.*;

public class CollectTreasure implements Goal, Observer {
	
	private String name = "treasure";
	private Dungeon dungeon;
	private boolean state = false;
	private int treasureCounter;

	public CollectTreasure(Dungeon d) {
		super();
		this.dungeon = d;
	}
	public boolean accomplished() {
		return state;
	}
	
	public void update(Subject s) {
		if (s instanceof Exit) {
			Exit e = (Exit) s;
			if (e.getState() == true) {
				state = true;
			}
		}
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
