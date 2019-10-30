package unsw.dungeon;

public class Treasure extends Entity {
	
	private String name;

	public Treasure(int x, int y, String name) {
		super(x, y);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
