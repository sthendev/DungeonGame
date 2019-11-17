package unsw.dungeon;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class AStarSearcher implements Searcher {

	@Override
	public Path search(Movable mover, Tile target) {
		Path path = new Path();
		if (target == null) return path;
		List<Node> closed = new ArrayList<>();
		PriorityQueue<Node> open = new PriorityQueue<Node>(5, new Comparator<Node>() {
			@Override
			public int compare(Node n1, Node n2) {
				return n1.totalCost() < n2.totalCost() ? -1 : n1.totalCost() > n2.totalCost() ? 1 : 0;
			}
		});
		open.add(new Node(mover.getCurrentTile(), null, mover.getCurrentTile().distToTile(target)));
		while (!open.isEmpty()) {
			Node cur = open.poll();
			closed.add(cur);
			if (cur.getTile() == target) {
				while (cur != null) {
					path.addFront(cur.getTile());
					cur = cur.getPrev();
				}
				return path;
			}
			for (Tile t : mover.getValidMovesFromTile(cur.getTile())) {
				Node n = new Node(t, cur, t.distToTile(target));
				if (!closed.contains(n)) {
					if (open.contains(n)) {
						if (open.removeIf((Node m) -> m.equals(n) && m.getPathCost() > n.getPathCost())) {
							open.add(n);
						}
					} else {
						open.add(n);
					}
				}
			}
		}
		return path;
	}

}
