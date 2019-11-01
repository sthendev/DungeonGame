package unsw.dungeon;
import java.util.*;

public class Sword extends Entity {
	
	private String name;
	private int hits;
	
	public Sword(Dungeon d, int x, int y, String name, int hits) {
		super(d, x, y);
		this.name = name;
		this.hits = hits;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getHits() {
		return hits;
	}
	
	public void useHits() {
		this.hits--;
	}
	
	
}
