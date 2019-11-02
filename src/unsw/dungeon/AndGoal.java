package unsw.dungeon;

public class AndGoal extends CompositeGoal {
	
	@Override
	public boolean satisfied() {
		for (Goal goal : getGoals()) {
			if (!goal.satisfied()) return false;
		}
		return true;
	}

}
