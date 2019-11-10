package unsw.dungeon;
import java.util.*;

import unsw.dungeon.Observer;

public class Inventory implements Subject {
	
	private ArrayList<Treasure> treasure;
	private Key key;
	private Sword sword;
	private int invincibleTime;
	private int freezeTime;
    private List<Observer> observers;

	public Inventory() {
		super();
		this.treasure = new ArrayList<Treasure>();
		this.key = null;
		this.sword = null;
		this.invincibleTime = 0;
		this.freezeTime = 0;
        this.observers = new ArrayList<>();
	}
	
	public ArrayList<Treasure> getTreasure() {
		return treasure;
	}

	public void addTreasure(Treasure t) {
		treasure.add(t);
		notifyObservers();
	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
		notifyObservers();
	}

	public Sword getSword() {
		return sword;
	}

	public void setSword(Sword sword) {
		this.sword = sword;
		notifyObservers();
	}
	
	public void useSword() {
		sword.useHits();
		if (sword.getHits() == 0) {
			setSword(null);
		}
		notifyObservers();
	}
	
	public int getInvincibleTime() {
		return invincibleTime;
	}
	
	public int getFreezeTime() {
		return freezeTime;
	}

	public void pickPotion(TurnBasedPotion p) {
		if (p instanceof InvincibilityPotion) {
			this.invincibleTime += p.getMoves();
		} else if (p instanceof FreezePotion) {
			this.freezeTime += p.getMoves();
			this.invincibleTime = 0;
		}
		notifyObservers();
	}
	
	public void usePotions() {
		if (invincibleTime > 0) invincibleTime--;
		if (freezeTime > 0) freezeTime--;
		notifyObservers();
	}
	
	public List<Observer> getObservers() {
		return observers;
	}
	
	@Override
	public void addObserver(Observer obj) {
		observers.add(obj);
	}

	@Override
	public void removeObserver(Observer obj) {
		observers.remove(obj);
	}
	
	@Override
	public void notifyObservers() {
		for (Observer o : getObservers()) {
			o.update(this);
		}
	}
}
