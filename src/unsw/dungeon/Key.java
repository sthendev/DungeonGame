package unsw.dungeon;

public class Key extends Entity {

	private Door door;
	private String name;

	public Key(int x, int y, Door door) {
		super(x, y);
		this.door = door;
		this.name = door.getName();
	}
	

	public Door getDoor() {
		return door;
	}

	public void setDoor(Door door) {
		this.door = door;
	}


	public String getName() {
		return door.getName();
	}

	
	
	
}
