package unsw.dungeon;

public class Potion extends Entity {
	
	private String name;
	private Player player;

	public Potion(Dungeon d, int x, int y, String name) {
		super(d, x, y);
		this.name = name;
		this.player = null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	@Override
	public void handleInteraction(Entity e) {
		if (e instanceof Player) {
			Player p = (Player) e;
			p.pickPotion(this);
			setPlayer(p);
			getDungeon().removeEntity(this, getX(), getY());
			notifyObservers();
		}
	}
	
}
