package unsw.dungeon;


public class Maze implements Goal, Observer {

	private String name = "maze";
	private Dungeon dungeon;
	private boolean state = false;

	public Maze (Dungeon d) {
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
	
	public Dungeon getDungeon() {
		return dungeon;
	}

	public void setDungeon(Dungeon dungeon) {
		this.dungeon = dungeon;
	}

	public boolean getState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}
}
