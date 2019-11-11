package unsw.dungeon;
import java.util.*;

import unsw.dungeon.Observer;

public class Inventory implements Subject {
	
	private List<Entity> items;
    private List<Observer> observers;

	public Inventory() {
		this.items = new ArrayList<>();
        this.observers = new ArrayList<>();
	}
	
	public int getTreasureCount() {
		int count = 0;
		for (Entity item : items) {
			if (item instanceof Treasure) count++;
		}
		return count;
	}
	
	public void addItem(Entity item) {
		if (item instanceof TurnBasedPotion) {
			addPotion((TurnBasedPotion) item);
		} else {
			items.add(item);
		}
		notifyObservers();
	}
	
	public void removeItem(Entity item) {
		items.remove(item);
		notifyObservers();
	}

	public Key getKey() {
		for (Entity item : items) {
			if (item instanceof Key) return (Key) item;
		}
		return null;
	}

	public Sword getSword() {
		for (Entity item : items) {
			if (item instanceof Sword) return (Sword) item;
		}
		return null;
	}
	
	public void useSword() {
		List<Entity> itemsCopy = new ArrayList<>(items);
		for (Entity item : itemsCopy) {
			if (item instanceof Sword) {
				Sword sword = (Sword) item;
				sword.useHit();
				if (sword.getHits() <= 0) {
					removeItem(sword);
				}
				break;
			}
		}
		notifyObservers();
	}
	
	public InvincibilityPotion getInvincibilityPotion() {
		for (Entity item : items) {
			if (item instanceof InvincibilityPotion) return (InvincibilityPotion) item;
		}
		return null;
	}
	
	public FreezePotion getFreezePotion() {
		for (Entity item : items) {
			if (item instanceof FreezePotion) return (FreezePotion) item;
		}
		return null;
	}

	public void addPotion(TurnBasedPotion p) {
		if (p instanceof InvincibilityPotion) {
			InvincibilityPotion existing = getInvincibilityPotion();
			if (existing != null) { 
				existing.extendMoves(p.getMoves());
			} else {
				items.add(p);
			}
		} else if (p instanceof FreezePotion) {
			FreezePotion existing = getFreezePotion();
			if (existing != null) { 
				existing.extendMoves(p.getMoves());
			} else {
				items.add(p);
			}
		}
	}
	
	public void usePotions() {
		List<Entity> itemsCopy = new ArrayList<>(items);
		for (Entity item : itemsCopy) {
			if (item instanceof TurnBasedPotion) {
				TurnBasedPotion potion = (TurnBasedPotion) item;
				potion.useMove();
				if (potion.getMoves() <= 0) {
					removeItem(potion);
				}
			}
		}
		notifyObservers();
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
		for (Observer o : observers) {
			o.update(this);
		}
	}
}
