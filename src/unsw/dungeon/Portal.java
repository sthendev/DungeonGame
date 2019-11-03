package unsw.dungeon;

public class Portal extends Entity {
	
	private int id;
	private Portal linkedPortal;
	
	public Portal(int id, Tile position) {
		super(position);
		this.id = id;
		this.linkedPortal = null;
	}
	
	public int getId() {
		return id;
	}
	
	public void setLinkedPortal(Portal linkedPortal) {
		this.linkedPortal = linkedPortal;
	}
	
	@Override
	public boolean isBlocking(Movable mover) {
		if (mover instanceof Enemy) return true;
		else if (mover instanceof Boulder) return true;
		return false;
	}
	
	@Override
	public void notifyComing(Movable mover) {
		if (mover instanceof Player) {
			Player player = (Player) mover;
			player.setCurrentTile(linkedPortal.getCurrentTile());
		}
	}
}
