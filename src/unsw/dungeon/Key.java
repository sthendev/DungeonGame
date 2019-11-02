package unsw.dungeon;

public class Key extends Entity {
	
	private int id;
	
	public Key(int id, Tile position) {
		super(position);
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	@Override
	public void meet(Movable mover) {
		if (mover instanceof Player) {
			Player player = (Player) mover;
			Key playerKey = player.getKey();
			if (playerKey != null) player.dropItem(playerKey);
			getPosition().removeEntity(this);
			player.addItem(this);
		}
	}
}
