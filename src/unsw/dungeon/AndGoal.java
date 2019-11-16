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
	public String toString() {
		String message = "(";
		
		for (int i = 0; i < getGoals().size(); i++) {
			message += getGoals().get(i).toString();
			if (i < getGoals().size() - 1) {
				message += " AND ";
			}
		}
		
		message += ")";
		
		return message;
	}

}
