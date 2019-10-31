package unsw.dungeon;

public class killEnemy implements Goal {
	
	private String name = "enemy";
	private Dungeon dungeon;

	public killEnemy(Dungeon d) {
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
		if (dungeon.getEnemies().size() == 0) {
			return true;
		}
		return false;
	}
	
}
