package unsw.dungeon;

public class Key extends Entity {
	
	private int id;

	public Key(int id, Tile tile) {
		super(tile);
		this.id = id;
	}
	
	@Override
	public void notifyComing(Movable e) {
		if (e instanceof Player) {
			Player p = (Player) e;
			Key k = p.keyHeld();
			if (k != null) {
				p.dropItem(k, getCurrentTile());
			}
			p.pickItem(this);
			getCurrentTile().removeEntity(this);
		}
	}


	public int getId() {
		return id;
	}

}
