package unsw.dungeon;

public class OrGoal extends Composite {

	public OrGoal(String name, Dungeon dungeon) {
		super(name, dungeon);
	}

	@Override
	public boolean accomplished() {
		boolean result = false;
		for (Goal g : getChildren()) {
			result |= g.accomplished();
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
