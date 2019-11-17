package unsw.dungeon;

public abstract class TurnBasedPotion extends Entity {
	
	private int moves;

	public TurnBasedPotion(Tile position) {
		super(position);
		this.moves = 5;
	}
	
	public TurnBasedPotion(Tile position, int moves) {
		super(position);
		this.moves = moves;
	}
	
	/**
	 * 
	 * @return remaining move uses of potion
	 */
	public int getMoves() {
		return moves;
	}
	
	/**
	 * consume a move use of potion
	 */
	public void useMove() {
		this.moves--;
	}
	
	/**
	 * add more move uses to potion
	 * @param moves
	 */
	public void extendMoves(int moves) {
		this.moves += moves;
	}
	
	@Override
	public void notifyComing(Movable e) {
		if (e instanceof Player) {
			Player p = (Player) e;
			p.pickItem(this);
			getCurrentTile().removeEntity(this);
		}
	}
	
}
