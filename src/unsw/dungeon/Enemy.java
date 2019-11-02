package unsw.dungeon;

public class Enemy extends Movable {
	
	private boolean alarmed;
	private Searcher ghostSearcher;
	
	MovementStrategy strategy;

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Enemy(Dungeon dungeon, Tile position, MovementStrategy strategy) {
        super(dungeon, position);
        this.alarmed = false;
        this.strategy = strategy;
        this.ghostSearcher = new GhostSearcher();
    }
    
    public void setStrategy(MovementStrategy strategy) {
    	this.strategy = strategy;
    }
    
    public boolean playerInSight() {
    	Path tilesToPlayer = ghostSearcher.search(this, getDungeon().getPlayer().getPosition());
    	if (tilesToPlayer.allTransparent(this)) return true;
    	return false;
    }
    
    public void move() {
    	if (!alarmed && playerInSight()) this.alarmed = true;
    	if (!alarmed) return;
    	
    	Tile bestMove = strategy.getBestMove(this);
    	
    	if (!bestMove.equals(getPosition())) moveMe(bestMove);
    }
    
    public void dies() {
    	if (getPosition() != null) getPosition().movedOff(this);
    	getDungeon().killEnemy(this);
    	ceaseExistence();
    	notifyObservers();
    }
    
    @Override
    public boolean isBlocking(Movable mover) {
    	if (mover instanceof Enemy) return true;
    	return false;
    }

	@Override
	public void meet(Movable mover) {
		if (mover instanceof Player) {
			Player player = (Player) mover;
			if (player.isInvincible()) {
				dies();
			} else if (player.hasSword()) {
				player.attack();
				dies();
			} else {
				player.dies();
			}
			
		}
	}
	
}
