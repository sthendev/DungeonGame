package unsw.dungeon;

public class Boulder extends Entity {
	
	private int index;

	public Boulder(int x, int y, int index) {
		super(x, y);
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	
}
