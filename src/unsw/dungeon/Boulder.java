package unsw.dungeon;

public class Boulder extends Entity {
	
	public Boulder(Dungeon d, int x, int y, int index) {
		super(d, x, y);
	}

	@Override
	public void handleInteraction(Entity e) {
		if (e instanceof Player) {
			Player p = (Player) e;
			int xMove = p.getLastXMove();
			int yMove = p.getLastYMove();
			xMove += getX();
			yMove += getY();
			if (getDungeon().canMove(this, xMove, yMove)) {
				x().set(xMove);
				y().set(yMove);
				notifyObservers();
			}
		}
	}

	
}
