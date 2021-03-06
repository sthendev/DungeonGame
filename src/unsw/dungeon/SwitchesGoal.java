package unsw.dungeon;

public class SwitchesGoal implements Goal, Observer {
	
	private String name;
	private int untriggeredSwitches;
	
	public SwitchesGoal() {
		this.name = "switches";
		this.untriggeredSwitches = 0;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public void update(Subject obj) {
		if (obj instanceof FloorSwitch) {
			FloorSwitch floorSwitch = (FloorSwitch) obj;
			if (floorSwitch.isTriggered()) {
				this.untriggeredSwitches--;
			} else {
				this.untriggeredSwitches++;
			}
		}
		
	}

	@Override
	public boolean satisfied() {
		return untriggeredSwitches == 0;
	}
	
	@Override
	public void linkEntity(Entity entity) {
		if (entity instanceof FloorSwitch) {
			entity.addObserver(this);
			this.untriggeredSwitches++;
		}
	}
	
	@Override
	public String message() {
		String message = "";
		
		message += satisfied() ? "\u2611 " : "\u2610 ";
		message += "Trigger all of the floor switches";
		
		return message;
	}

}
