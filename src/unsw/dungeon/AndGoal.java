package unsw.dungeon;

public class AndGoal extends Composite {

	
	public AndGoal(String name, Dungeon dungeon) {
		super(name, dungeon);
	}

	@Override
	public boolean accomplished() {
		boolean result = true;
		for (Goal g : getChildren()) {
			result &= g.accomplished();
		}
		return result;
	}
	
	public void add(Goal g) {
		getChildren().add(g);
	}
	
	public void remove(Goal g) {
		getChildren().remove(g);
	}
}
