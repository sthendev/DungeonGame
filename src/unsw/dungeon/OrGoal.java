package unsw.dungeon;

public class OrGoal extends CompositeGoal {

	@Override
	public boolean satisfied() {
		for (Goal goal : getGoals()) {
			if (goal.satisfied()) return true;
		}
		return false;
	}

}
