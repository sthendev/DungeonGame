package unsw.dungeon;

import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DoorUIUpdater extends UIUpdater {
	private Image openDoorImage;
	
	public DoorUIUpdater(ImageView node, Image openDoorImage) {
		super(node);
		this.openDoorImage = openDoorImage;
	}
	
	@Override
	public void update(Subject obj) {
		if (obj instanceof Door) {
			Door door = (Door) obj;
			if (door.isOpened()) {
				getNode().setImage(openDoorImage);
			}
			if (door.playerHasKey()) {
				ColorAdjust colorAdjust = new ColorAdjust();
				colorAdjust.setBrightness(0.2);
				colorAdjust.setHue(0.2);
				getNode().setEffect(colorAdjust);
			} else {
				getNode().setEffect(null);
			}
			super.update(obj);
		}
	}
}
