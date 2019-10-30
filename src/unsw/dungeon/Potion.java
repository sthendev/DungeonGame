package unsw.dungeon;

public class Potion extends Entity {
	
	private String name;

	public Potion(int x, int y, String name) {
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
