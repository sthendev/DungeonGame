package unsw.dungeon;

public class Triggered implements State {
	
	private Switch s;
	
	public Triggered(Switch s) {
		this.s = s;
	}
	
	public void toTrigger() {
		System.err.println("Floor switch already triggered");
	}
	
	public void toUntrigger() {
		s.setState(s.getUntriggered());
	}
}
