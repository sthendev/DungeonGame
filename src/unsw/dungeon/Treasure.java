package unsw.dungeon;

public class Treasure extends Entity {
	
	private String name;

	public Treasure(Dungeon d, int x, int y, String name) {
		super(d, x, y);
		this.name = name;
	}
	
	@Override
	public void handleInteraction(Entity e) {
		if (e instanceof Player) {
			Player p = (Player) e;
			p.pickTreasure(this);
			getDungeon().removeEntity(this, p.getX(), p.getY());
			notifyObservers();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
