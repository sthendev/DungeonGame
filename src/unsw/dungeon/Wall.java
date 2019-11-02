package unsw.dungeon;

public class Wall extends Entity {

    public Wall(Tile position) {
        super(position);
    }
    
    @Override
    public boolean isBlocking(Movable mover) {
    	return true;
    }
    
    @Override
	public void notifyComing(Movable e) {
		//Do nothing
	}
    
    @Override
    public boolean isTransparent() {
    	return false;
    }

}
