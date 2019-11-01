package unsw.dungeon;

public class Key extends Entity {

	private Door door;
	private String name;
	private Player player;

	public Key(int x, int y, Door door, Dungeon d) {
		super(d, x, y);
		this.door = door;
		this.name = door.getName();
		this.player = null;
	}
	
	@Override
	public void handleInteraction(Entity e) {
		if (e instanceof Player) {
			Player p = (Player) e;
			if (p.keyHeld() != null) {
				Key k = p.keyHeld();
				int x = p.getX();
				int y = p.getY();
				k.setX(x);
				k.setY(y);
				getDungeon().addEntity(k, x, y);
			}
			p.pickKey(this);
			setPlayer(p);
			getDungeon().removeEntity(this, getX(), getY());
			notifyObservers();
			// How does javaFX know the key has swapped?
		}
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

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

}
