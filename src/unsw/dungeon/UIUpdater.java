package unsw.dungeon;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class UIUpdater implements Observer {
	
	private ImageView node;
	private Image originalImage;
	
	UIUpdater(ImageView node) {
		this.node = node;
		this.originalImage = node.getImage();
	}
	
	/**
	 * 
	 * @return associated UI node
	 */
	public ImageView getNode() {
		return node;
	}
	
	/**
	 * 
	 * @return original image of node
	 */
	public Image getOriginalImage() {
		return originalImage;
	}

	@Override
	public void update(Subject obj) {
		if (obj instanceof Entity) {
			Entity entity = (Entity) obj;
			if (entity.getCurrentTile() == null) {
				node.setImage(null);
			} else {
				if (node.getImage() == null) {
					node.setImage(originalImage);
				}
				GridPane.setColumnIndex(node, entity.getX());
				GridPane.setRowIndex(node, entity.getY());
			}
		}
	}
	
}
