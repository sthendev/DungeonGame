package unsw.dungeon;
import java.util.*;

public class Inventory {
	
	private ArrayList<Treasure> treasure;
	private Key key;
	private Sword sword;
	private int invincibleTime;

	public Inventory() {
		super();
		this.treasure = new ArrayList<Treasure>();
		this.key = null;
		this.sword = null;
		this.invincibleTime = 0;
	}

	public ArrayList<Treasure> getTreasure() {
		return treasure;
	}

	public void setTreasure(ArrayList<Treasure> treasure) {
		this.treasure = treasure;
	}
	
	public void addTreasure(Treasure t) {
		treasure.add(t);
		treasureCounter++;
	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public Sword getSword() {
		return sword;
	}

	public void setSword(Sword sword) {
		this.sword = sword;
	}

	public int SwordHit() {
		return sword.getHits();
	}
	
	public void useSword() {
		sword.useHits();
	}
	
	public int getInvincibleTime() {
		return invincibleTime;
	}

	public void pickPotion(int invincibleTime) {
		this.invincibleTime += invincibleTime;
	}
	
	//To-do: count down of invincible time
	
}
