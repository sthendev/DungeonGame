package unsw.dungeon;

public class Key extends Entity {

	private Player player;
	private int id;

	public Key(int id, int x, int y, Dungeon d) {
		super(d, x, y);
		this.id = id;
		this.player = null;
	}
	
	@Override
	public void notifyComing(Movable e) {
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


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
