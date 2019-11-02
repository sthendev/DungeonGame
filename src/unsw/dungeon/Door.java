package unsw.dungeon;


public class Door extends Entity {
	
	private String name;
	private String state;
	private int id;
	
	public Door(int id, Tile t, String name) {
		super(t);
		this.name = name;
		this.id = id;
		this.state = "untriggered";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
		notifyObservers();
	}
	
	public boolean isRight(Key k) {
		if (k.getId() == id) {
			return true;
		}
		return false;
	}
	
	@Override
	public void notifyComing(Movable e) {
		if (e instanceof Player && state.equals("untriggered")) {
			Player p = (Player) e;
			if (isRight(p.keyHeld())) {
				setState("triggered");
				p.useKey();
			}
		}
	}
	
}
