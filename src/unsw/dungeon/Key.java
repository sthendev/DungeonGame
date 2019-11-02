package unsw.dungeon;

public class Key extends Entity {

	private Player player;
	private int id;

	public Key(int id, Tile t) {
		super(t);
		this.id = id;
		this.player = null;
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
			setPlayer(p);
			getCurrentTile().removeEntity(this);
		}
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

}
