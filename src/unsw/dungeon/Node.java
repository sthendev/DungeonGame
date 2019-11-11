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
	
	public Node getPrev() {
		return prev;
	}
	
	public int getPathCost() {
		if (prev == null) {
			return 0;
		} else {
			return prev.getPathCost() + 1;
		}
	}
	
	public double estimateDistToTarget() {
		return distToTarget;
	}
	
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
