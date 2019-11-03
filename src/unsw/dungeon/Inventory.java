package unsw.dungeon;
import java.util.*;

import unsw.dungeon.Observer;

public class Inventory implements Subject {
	
	private ArrayList<Treasure> treasure;
	private Key key;
	private Sword sword;
	private int invincibleTime;
    private List<Observer> observers;

	public Inventory() {
		super();
		this.treasure = new ArrayList<Treasure>();
		this.key = null;
		this.sword = null;
		this.invincibleTime = 0;
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

	public void pickPotion(int increment) {
		invincibleTime += increment;
		notifyObservers();
	}
	
	public void usePotion() {
		invincibleTime--;
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
