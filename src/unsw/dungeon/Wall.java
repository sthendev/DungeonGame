package unsw.dungeon;

public class Wall extends Entity {

    public Wall(Tile position) {
        super(position);
    }
    
    @Override
    public boolean isBlocking(Entity entity) {
    	return true;
    }

}
