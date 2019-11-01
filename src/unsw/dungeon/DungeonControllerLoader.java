package unsw.dungeon;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * A DungeonLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonControllerLoader extends DungeonLoader {

    private List<ImageView> entities;

    //Images
    private Image playerImage;
    private Image armedPlayer;
    private Image wallImage;
    private Image enemyImage;
    private Image exitImage;
    private Image swordImage;
    private Image invincibilityPotionImage;
    
    public DungeonControllerLoader(String filename)
            throws FileNotFoundException {
        super(filename);
        entities = new ArrayList<>();
        playerImage = new Image("/human_new.png");
        armedPlayer = new Image("/human_new_sword.png");
        wallImage = new Image("/brick_brown_0.png");
        enemyImage = new Image("/gnome.png");
        exitImage = new Image("/exit.png");
        swordImage = new Image("/greatsword_1_new.png");
        invincibilityPotionImage = new Image("/brilliant_blue_new.png");
    }

    @Override
    public void onLoad(Entity player) {
        ImageView view = new ImageView(playerImage);
        addEntity(player, view);
    }

    @Override
    public void onLoad(Wall wall) {
        ImageView view = new ImageView(wallImage);
        addEntity(wall, view);
    }
    
    @Override
    public void onLoad(Enemy enemy) {
    	ImageView view = new ImageView(enemyImage);
    	addEntity(enemy, view);
    }
    
    @Override
    public void onLoad(Exit exit) {
    	ImageView view = new ImageView(exitImage);
    	addEntity(exit, view);
    }
    
    @Override
    public void onLoad(Sword sword) {
    	ImageView view = new ImageView(swordImage);
    	addEntity(sword, view);
    }
    
    @Override
    public void onLoad(InvincibilityPotion potion) {
    	ImageView view = new ImageView(invincibilityPotionImage);
    	addEntity(potion, view);
    }

    private void addEntity(Entity entity, ImageView view) {
        trackPosition(entity, view);
        entities.add(view);
    }

    /**
     * Set a node in a GridPane to have its position track the position of an
     * entity in the dungeon.
     *
     * By connecting the model with the view in this way, the model requires no
     * knowledge of the view and changes to the position of entities in the
     * model will automatically be reflected in the view.
     * @param entity
     * @param node
     */
    private void trackPosition(Entity entity, ImageView node) {
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());
        entity.addObserver(getUIUpdater(entity, node));
    }
    
    private UIUpdater getUIUpdater(Entity entity, ImageView node) {
    	if (entity instanceof Player) return new PlayerUIUpdater(node, armedPlayer);
    	return new UIUpdater(node);
    }

    /**
     * Create a controller that can be attached to the DungeonView with all the
     * loaded entities.
     * @return
     * @throws FileNotFoundException
     */
    public DungeonController loadController() throws FileNotFoundException {
        return new DungeonController(load(), entities);
    }


}
