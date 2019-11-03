package unsw.dungeon.test;

import unsw.dungeon.Entity;
import unsw.dungeon.Goal;

public class DummyGoal implements Goal {
	
	private boolean satisfied;
	
	public DummyGoal() {
		this.satisfied = false;
	}
	
	public void setSatisfied() {
		this.satisfied = true;
	}
	
	public void setUnsatisfied() {
		this.satisfied = false;
	}

	@Override
	public boolean satisfied() {
		return satisfied;
	}

	@Override
	public void linkEntity(Entity entity) {
		
	}
	
}
