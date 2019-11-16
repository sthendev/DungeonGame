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
    private Image armedPlayerImage;
    private Image hammerPlayerImage;
    private Image wallImage;
    private Image enemyImage;
    private Image exitImage;
    private Image swordImage;
    private Image hammerImage;
    private Image invincibilityPotionImage;
    private Image boulderImage;
    private Image floorSwitchImage;
    private Image treasureImage;
    private Image keyImage;
    private Image closedDoorImage;
    private Image openDoorImage;
    private Image portalImage;
    private Image frozenEnemyImage;
    private Image freezingPotionImage;
    private Image ghostPotionImage;
    
    private DungeonController controller;
    
    public DungeonControllerLoader(String filename)
            throws FileNotFoundException {
        super(filename);
        controller = null;
        entities = new ArrayList<>();
        playerImage = new Image("/human_new.png");
        armedPlayerImage = new Image("/human_new_sword.png");
        hammerPlayerImage = new Image("/human_hammer.png");
        wallImage = new Image("/brick_brown_0.png");
        enemyImage = new Image("/gnome.png");
        exitImage = new Image("/exit.png");
        swordImage = new Image("/greatsword_1_new.png");
        hammerImage = new Image("/hammer.png");
        invincibilityPotionImage = new Image("/brilliant_blue_new.png");
        boulderImage = new Image("/boulder.png");
        floorSwitchImage = new Image("/pressure_plate.png");
        treasureImage = new Image("/gold_pile.png");
        keyImage = new Image("/key.png");
        closedDoorImage = new Image("/closed_door.png");
        openDoorImage = new Image("/open_door.png");
        portalImage = new Image("/portal.png");
        frozenEnemyImage = new Image("/gnome_frozen.png");
        freezingPotionImage = new Image("/freeze_potion.png");
        ghostPotionImage = new Image("/ghost_potion.png");
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
    
    @Override
    public void onLoad(Boulder boulder) {
    	ImageView view = new ImageView(boulderImage);
    	addEntity(boulder, view);
    }
    
    @Override
    public void onLoad(FloorSwitch floorSwitch) {
    	ImageView view = new ImageView(floorSwitchImage);
    	addEntity(floorSwitch, view);
    }
    
    @Override
    public void onLoad(Treasure treasure) {
    	ImageView view = new ImageView(treasureImage);
    	addEntity(treasure, view);
    }
    
    @Override
    public void onLoad(Key key) {
    	ImageView view = new ImageView(keyImage);
    	addEntity(key, view);
    }
    
    @Override
    public void onLoad(Door door) {
    	ImageView view =  new ImageView(closedDoorImage);
    	addEntity(door, view);
    }
    
    @Override
    public void onLoad(Portal portal) {
    	ImageView view = new ImageView(portalImage);
    	addEntity(portal, view);
    }
    
    @Override
    public void onLoad(FreezePotion potion) {
    	ImageView view = new ImageView(freezingPotionImage);
    	addEntity(potion, view);
    }
    
    @Override
    public void onLoad(GhostPotion potion) {
    	ImageView view = new ImageView(ghostPotionImage);
    	addEntity(potion, view);
    }
    
    @Override
    public void onLoad(Hammer hammer) {
    	ImageView view = new ImageView(hammerImage);
    	addEntity(hammer, view);
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
    	if (entity instanceof Player) return new PlayerUIUpdater(node, armedPlayerImage, hammerPlayerImage);
    	if (entity instanceof Door) return new DoorUIUpdater(node, openDoorImage);
    	if (entity instanceof Enemy) return new EnemyUIUpdater(node, frozenEnemyImage);
    	return new UIUpdater(node);
    }

    /**
     * Create a controller that can be attached to the DungeonView with all the
     * loaded entities.
     * @return
     * @throws FileNotFoundException
     */
    public DungeonController loadController() throws FileNotFoundException {
    	this.controller = new DungeonController(load(), entities); 
        return this.controller;
    }


}
