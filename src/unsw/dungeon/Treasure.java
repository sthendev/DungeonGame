package unsw.dungeon;

public class Treasure extends Entity {
	

	public Treasure(Tile t) {
		super(t);
	}
	
	@Override
	public void notifyComing(Movable e) {
		if (e instanceof Player) {
			Player p = (Player) e;
			p.pickTreasure(this);
			getCurrentTile().removeEntity(this);
		}
	}
	
	
}
