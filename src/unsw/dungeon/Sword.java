package unsw.dungeon;
import java.util.*;

public class Sword extends Entity {
	
	private String name;
	private int hits;
	private Player player;
	
	public Sword(Dungeon d, Player p, int x, int y, String name, int hits) {
		super(d, x, y);
		this.name = name;
		this.hits = hits;
		this.player = p;
	}
	
	@Override
	public void handleInteraction(Entity e) {
		if (e instanceof Player) {
			Player p = (Player) e;
			if (p.getSword() != null) {
				Sword s = p.getSword();
				int x = p.getX();
				int y = p.getY();
				s.setX(x);
				s.setY(y);
				getDungeon().addEntity(s, x, y);
			}
			p.pickSword(this);
			setPlayer(p);
			getDungeon().removeEntity(this, getX(), getY());
			notifyObservers();
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
