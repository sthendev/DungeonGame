package unsw.dungeon;

public class Exit extends Entity {
	public Exit(int x, int y) {
        super(x, y);
    }
    
    @Override
    public boolean isBlocking(Entity entity) {
    	return true;
    }
    
    @Override
    public String toString() {
    	return super.toString() + " exit";
    }
}
