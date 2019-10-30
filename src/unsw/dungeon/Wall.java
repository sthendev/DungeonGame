package unsw.dungeon;

public class Wall extends Entity {

    public Wall(int x, int y) {
        super(x, y);
    }
    
    @Override
    public boolean isBlocking(Entity entity) {
    	return true;
    }
    
    @Override
    public String toString() {
    	return super.toString() + " wall";
    }

}
