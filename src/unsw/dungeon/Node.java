package unsw.dungeon;

public class Node {
	private Tile tile;
	private Node prev;
	private double distToTarget;
	
	public Node(Tile tile, Node prev, double distToTarget) {
		this.tile = tile;
		this.prev = prev;
		this.distToTarget = distToTarget;
	}
	
	public Tile getTile() {
		return tile;
	}
	
	/**
	 * 
	 * @return not that this node was reached from
	 */
	public Node getPrev() {
		return prev;
	}
	
	/**
	 * 
	 * @return total cost of path used to reach this node
	 */
	public int getPathCost() {
		if (prev == null) {
			return 0;
		} else {
			return prev.getPathCost() + 1;
		}
	}
	
	/**
	 * 
	 * @return estimated cost to target tile
	 */
	public double estimateDistToTarget() {
		return distToTarget;
	}
	
	/**
	 * 
	 * @return sum of path cost and estimated cost
	 */
	public double totalCost() {
		return estimateDistToTarget() + getPathCost();
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof Node && ((Node) other).getTile() == getTile()) {
			return true;
		}
		return false;
	}
}
