package unsw.dungeon;
import java.util.*;

public class Sword extends Entity {
	
	private int hits;
	private Player player;
	
	public Sword(Tile position) {
		super(position);
		this.hits = 5;
	}
	
	@Override
	public void notifyComing(Movable e) {
		if (e instanceof Player) {
			Player p = (Player) e;
			if (p.getSword() != null) {
				Sword s = p.getSword();
				s.setCurrentTile(p.getCurrentTile());
				getCurrentTile().placeEntity(this);
			}
			p.pickSword(this);
			setPlayer(p);
			getCurrentTile().removeEntity(this);
		}
	}

	public int getHits() {
		return hits;
	}
	
	public void useHits() {
		this.hits--;
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
	
	
}
