package unsw.dungeon;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class EnemyUIUpdater extends UIUpdater {
	
	private Image frozenImage;

	EnemyUIUpdater(ImageView node, Image frozenImage) {
		super(node);
		this.frozenImage = frozenImage;
	}
	
	@Override
	public void update(Subject obj) {
		if (obj instanceof Enemy) {
			Enemy enemy = (Enemy) obj;
			Player player = enemy.getDungeon().getPlayer();
			if (player != null && player.isFreezing()) {
				getNode().setImage(frozenImage);
			} else {
				getNode().setImage(getOriginalImage());
			}
			super.update(enemy);
		}
	}
	
}
