package unsw.dungeon;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.effect.ColorAdjust;

public class PlayerUIUpdater extends UIUpdater {
	
	private Image armedImage;
	private Image hammerImage;
	
	public PlayerUIUpdater(ImageView node, Image armedImage, Image hammerImage) {
		super(node);
		this.armedImage = armedImage;
		this.hammerImage = hammerImage;
	}
	
	@Override
	public void update(Subject obj) {
		if (obj instanceof Player) {
			Player player = (Player) obj;
			if (player.hasSword()) {
				getNode().setImage(armedImage);
			} else if (player.hasHammer()) {
				getNode().setImage(hammerImage);
			} else {
				getNode().setImage(getOriginalImage());
			}
			
			if (player.isInvincible()) {
				ColorAdjust colorAdjust = new ColorAdjust();
				colorAdjust.setContrast(0.8);
				colorAdjust.setHue(0.2);
				getNode().setEffect(colorAdjust);
			} else if (player.isGhost()) {
				ColorAdjust colorAdjust = new ColorAdjust();
				colorAdjust.setSaturation(-1);
				getNode().setEffect(colorAdjust);
			} else {
				getNode().setEffect(null);
			}
			
			super.update(player);
		}
		
	}
}
