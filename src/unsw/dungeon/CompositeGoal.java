package unsw.dungeon;

import java.util.List;
import java.util.ArrayList;

public abstract class CompositeGoal implements Goal {
	
	private List<Goal> goals;
	
	public CompositeGoal() {
		goals = new ArrayList<>();
	}
	
	public List<Goal> getGoals() {
		return goals;
	}

	public void remove(Goal g) {
		goals.remove(g);
	}

	public void addGoal(Goal goal) {
		goals.add(goal);
	}
	
	@Override
	public void linkEntity(Entity entity) {
		for (Goal goal : getGoals()) {
			goal.linkEntity(entity);
		}
	}
}
