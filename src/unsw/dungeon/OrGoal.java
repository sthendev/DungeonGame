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
	public String toString() {
		String message = "(";
		
		for (int i = 0; i < getGoals().size(); i++) {
			message += getGoals().get(i).toString();
			if (i < getGoals().size() - 1) {
				message += " OR ";
			}
		}
		
		message += ")";
		
		return message;
	}

}
