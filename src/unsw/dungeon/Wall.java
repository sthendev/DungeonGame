package unsw.dungeon;

public class Wall extends Entity {

    public Wall(Tile position) {
        super(position);
    }
    
    public void destroy() {
    	getCurrentTile().removeEntity(this);
    	SoundPlayer sd = new SoundPlayer();
    	sd.playSound("break_wall.wav");
    }
    
    @Override
    public boolean isBlocking(Movable mover) {
    	if (mover instanceof Player) {
    		Player player = (Player) mover;
    		Tile oppositeTile = getCurrentTile().getOppositeTile(player.getCurrentTile());
			if (oppositeTile == null) return true;
    		if (player.isGhost()) {
    			if (oppositeTile.hasWall() || oppositeTile.hasClosedDoor()) return true;
    			else if (player.canMove(oppositeTile)) return false;
    		} else if (player.hasHammer()) {	
    			return false;
    		}
    	}
    	return true;
    }
    
    @Override
    public boolean isTransparent() {
    	return false;
    }
    
    @Override
    public void notifyComing(Movable mover) {
    	if (mover instanceof Player) {
    		Player p = (Player) mover;
			Tile oppositeTile = getCurrentTile().getOppositeTile(p.getPreviousTile());
			if (p.isGhost() && !oppositeTile.hasWall() && !oppositeTile.hasClosedDoor()) {
				oppositeTile.movedOn(p);
			} else if (p.hasHammer()) {
				p.useTool();
				destroy();
			} else {
				oppositeTile.movedOn(p);
			}
    	}
    }

}
