package unsw.dungeon;

public class Untriggered implements State {

	private Switch s;

	public Untriggered(Switch s) {
		super();
		this.s = s;
	}
	
	public void toTrigger() {
		s.setState(s.getTriggered());
	}
	
	public void toUntrigger() {
		System.err.println("Floor switch already untriggered.");
	}
}
