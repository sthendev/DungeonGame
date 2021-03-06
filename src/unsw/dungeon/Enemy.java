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
    
    /**
     * set strategy of enemy
     * @param strategy
     */
    public void setStrategy(MovementStrategy strategy) {
    	this.strategy = strategy;
    	notifyObservers();
    }
    
    /**
     * 
     * @return whether or not the player is currently in the enemy's line of site
     */
    public boolean playerInSight() {
    	Path tilesToPlayer = ghostSearcher.search(this, getDungeon().getPlayer().getCurrentTile());
    	if (tilesToPlayer.allTransparent(this)) return true;
    	return false;

    }
    
    /**
     * perform move
     */
    public void move() {
    	if (!alarmed && playerInSight()) this.alarmed = true;
    	if (!alarmed) return;
    	
    	Tile bestMove = strategy.getBestMove(this);
    	
    	if (!bestMove.equals(getCurrentTile())) moveMe(bestMove);
    }
    
    /**
     * handle death of enemy
     */
    public void dies() {
    	if (getCurrentTile() != null) getCurrentTile().movedOff(this);
    	getDungeon().removeEnemy(this);
    	ceaseExistence();
    	SoundPlayer sd = new SoundPlayer();
    	sd.playSound("hurt.wav");
    }
    
    @Override
    public boolean isBlocking(Movable mover) {
    	if (mover instanceof Enemy) return true;
    	else if (mover instanceof Boulder) return true;
    	return false;
    }

	@Override
	public void notifyComing(Movable mover) {
		if (mover instanceof Player) {
			Player player = (Player) mover;
			if (player.isInvincible()) {
				dies();
			} else if (player.hasSword()) {
				player.useTool();
				dies();
			} else {
				player.dies();
			}
		}
	}

	public MovementStrategy getStrategy() {
		return strategy;
	}
	
}
