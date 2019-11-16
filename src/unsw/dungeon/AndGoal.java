package unsw.dungeon;

public class AndGoal extends CompositeGoal {
	
	@Override
	public boolean satisfied() {
		for (Goal goal : getGoals()) {
			if (!goal.satisfied()) return false;
		}
		return true;
	}
	
	@Override
	public String message() {
		String message = "";
		
		message += satisfied() ? "\u2611 " : "\u2610 ";
		message += "Do all of the following:";
		
		return message;
	}

}
