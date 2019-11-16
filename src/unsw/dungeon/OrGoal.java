package unsw.dungeon;

public class OrGoal extends CompositeGoal {

	@Override
	public boolean satisfied() {
		for (Goal goal : getGoals()) {
			if (goal.satisfied()) return true;
		}
		return false;
	}
	
	@Override
	public String message() {
		String message = "";
		
		message += satisfied() ? "\u2611 " : "\u2610 ";
		message += "Do one of the following:";
		
		return message;
	}

}
