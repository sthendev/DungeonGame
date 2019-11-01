package unsw.dungeon;

public class Wall extends Entity {

    public Wall(Dungeon d, int x, int y) {
        super(d, x, y);
    }
    
    @Override
	public void handleInteraction(Entity e) {
		//Do nothing
	}
}
