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
	
	public List<Entity> getItems() {
		return items;
	}
	
	/**
	 * 
	 * @return count of treasure in the inventory
	 */
	public int getTreasureCount() {
		int count = 0;
		for (Entity item : items) {
			if (item instanceof Treasure) count++;
		}
		return count;
	}
	
	/**
	 * add specified item to inventory
	 * @param item
	 */
	public void addItem(Entity item) {
		if (item instanceof TurnBasedPotion) {
			addPotion((TurnBasedPotion) item);
		} else {
			items.add(item);
		}
    	SoundPlayer sd = new SoundPlayer();
    	sd.playSound(sd.getSoundForItem(item));
		notifyObservers();
	}
	
	/**
	 * remove specified item to inventory
	 * @param item
	 */
	public void removeItem(Entity item) {
		items.remove(item);
		notifyObservers();
	}
	
	/**
	 * 
	 * @return current key held or null if no key
	 */
	public Key getKey() {
		for (Entity item : items) {
			if (item instanceof Key) return (Key) item;
		}
		return null;
	}
	
	/**
	 * 
	 * @return current tool held or null if no tool
	 */
	public HitBasedTool getTool() {
		for (Entity item : items) {
			if (item instanceof HitBasedTool) return (HitBasedTool) item;
		}
		return null;
	}
	
	/**
	 * use a hit on the currently held tool
	 */
	public void useTool() {
		List<Entity> itemsCopy = new ArrayList<>(items);
		for (Entity item : itemsCopy) {
			if (item instanceof HitBasedTool) {
				HitBasedTool tool = (HitBasedTool) item;
				tool.useHit();
				if (tool.getHits() <= 0) {
					removeItem(tool);
				}
				break;
			}
		}
		notifyObservers();
	}
	
	/**
	 * 
	 * @return invincibility potion in inventory or null if none found
	 */
	public InvincibilityPotion getInvincibilityPotion() {
		for (Entity item : items) {
			if (item instanceof InvincibilityPotion) return (InvincibilityPotion) item;
		}
		return null;
	}
	
	/**
	 * 
	 * @return freeze potion in inventory or null if none found
	 */
	public FreezePotion getFreezePotion() {
		for (Entity item : items) {
			if (item instanceof FreezePotion) return (FreezePotion) item;
		}
		return null;
	}
	
	/**
	 * 
	 * @return ghost potion in enventory or null if none found
	 */
	public GhostPotion getGhostPotion() {
		for (Entity item : items) {
			if (item instanceof GhostPotion) return (GhostPotion) item;
		}
		return null;
	}
	
	/**
	 * process adding of potion to inventory
	 * @param p
	 */
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
		} else if (p instanceof GhostPotion) {
			GhostPotion existing = getGhostPotion();
			if (existing != null) { 
				existing.extendMoves(p.getMoves());
			} else {
				items.add(p);
			}
		}
	}
	
	/**
	 * process using of potions in inventory
	 */
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
