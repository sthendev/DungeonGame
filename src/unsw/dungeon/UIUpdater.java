package unsw.dungeon;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class UIUpdater implements Observer {
	
	Node node;
	
	UIUpdater(Node node) {
		this.node = node;
	}

	@Override
	public void update(Subject obj) {
		if (obj instanceof Entity) {
			Entity entity = (Entity) obj;
			GridPane.setColumnIndex(node, entity.getX());
			GridPane.setRowIndex(node, entity.getY());
		}
	}
	
}
