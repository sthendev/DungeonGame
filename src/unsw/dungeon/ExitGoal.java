package unsw.dungeon;

public class ExitGoal implements Goal, Observer {
	
	private String name;
	private boolean satisfied;
	
	public ExitGoal() {
		this.name = "exit";
		this.satisfied = false;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public void update(Subject obj) {
		if (obj instanceof Exit) {
			Exit exit = (Exit) obj;
			if (exit.playerIsHere()) {
				this.satisfied = true;
			} else {
				this.satisfied = false;
			}
		}
	}

	@Override
	public boolean satisfied() {
		return satisfied;
	}
	
	@Override
	public void linkEntity(Entity entity) {
		if (entity instanceof Exit) {
			entity.addObserver(this);
		}
	}
	
	@Override
	public String toString() {
		return "reach the exit";
	}

}
