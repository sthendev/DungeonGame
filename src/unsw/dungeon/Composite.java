package unsw.dungeon;
import java.util.*;

abstract public class Composite implements Goal {
	
	private String name;
	private Dungeon dungeon;
	private ArrayList<Goal> children;
	
	public Composite(String name, Dungeon dungeon) {
		super();
		this.name = name;
		this.dungeon = dungeon;
		this.children = new ArrayList<Goal>();
	}

	abstract public boolean accomplished();
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Dungeon getDungeon() {
		return dungeon;
	}

	public void setDungeon(Dungeon dungeon) {
		this.dungeon = dungeon;
	}

	public ArrayList<Goal> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<Goal> children) {
		this.children = children;
	}

	
}
