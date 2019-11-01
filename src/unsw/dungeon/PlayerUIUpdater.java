package unsw.dungeon;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PlayerUIUpdater extends UIUpdater {
	
	private Image armedImage;
	
	public PlayerUIUpdater(ImageView node, Image armedImage) {
		super(node);
		this.armedImage = armedImage;
	}
	
	@Override
	public void update(Subject obj) {
		if (obj instanceof Player) {
			Player player = (Player) obj;
			super.update(player);
			if (player.hasSword()) {
				getNode().setImage(armedImage);
			} else {
				getNode().setImage(getOriginalImage());
			}
		}
	}
}
