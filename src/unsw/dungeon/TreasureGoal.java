package unsw.dungeon;

public class TreasureGoal implements Goal, Observer {
	
	private String name;
	private int uncollectedTreasure;
	
	public TreasureGoal() {
		this.name = "treasure";
		this.uncollectedTreasure = 0;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public void update(Subject obj) {
		if (obj instanceof Treasure) {
			Treasure treasure = (Treasure) obj;
			if (treasure.isCollected()) {
				this.uncollectedTreasure--;
				treasure.removeObserver(this);
			}
		}
	}

	@Override
	public boolean satisfied() {
		return uncollectedTreasure == 0;
	}
	
	@Override
	public void linkEntity(Entity entity) {
		if (entity instanceof Treasure) {
			entity.addObserver(this);
			this.uncollectedTreasure++;
		}
	}
	
	@Override
	public String message() {
		String message = "";
		
		message += satisfied() ? "\u2611 " : "\u2610 ";
		message += "Collect all the treasure";
		
		return message;
	}
	
}
