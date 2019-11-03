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
			if (p.keyHeld() != null) {
				Key k = p.keyHeld();
				k.setCurrentTile(p.getCurrentTile());
				getCurrentTile().placeEntity(k);
			}
			p.pickKey(this);
			getCurrentTile().removeEntity(this);
		}
	}


	public int getId() {
		return id;
	}

}
