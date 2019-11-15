package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.Node;

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {

    @FXML
    private GridPane squares;
    
    @FXML
    private GridPane inventory;

    private List<ImageView> initialEntities;

    private Player player;

    private Dungeon dungeon;

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
    }

    @FXML
    public void initialize() {
        Image ground = new Image("/dirt_0_new.png");

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }

        for (ImageView entity : initialEntities) {
        	squares.getChildren().add(entity);
        }
        
        inventory.setLayoutX(32 * dungeon.getWidth());
        
        for (int y = 0; y < dungeon.getHeight(); y++) {
        	inventory.add(new ImageView(new Image("/empty.png")), 0, y);
        }
    }

    @FXML
    public void handleKeyPress(KeyEvent event) {
    	if (dungeon.gameOver()) return;
        switch (event.getCode()) {
        case UP:
        	player.Move(0, 1);
            break;
        case DOWN:
        	player.Move(0, -1);
            break;
        case LEFT:
        	player.Move(-1, 0);
            break;
        case RIGHT:
        	player.Move(1, 0);
            break;
        case S:
        	player.stay();
        	break;
        default:
            break;
        }
        updateInventory();
    }
    
    public void updateInventory() {
    	inventory.getChildren().clear();
    	
    	int inventImageCount = 0;
    	int treasureCount = 0;
    	
    	for (Entity item : player.getInventory().getItems()) {
    		if (item instanceof Treasure)  {
    			treasureCount++; 
    			continue;
    		}
    		addInventoryItem(item);
    		inventImageCount++;
    	}
    	
    	if (treasureCount > 0) {
    		addInventoryImage(new ImageView(new Image("gold_pile.png")), new Text(Integer.toString(treasureCount)));
    		inventImageCount++;
    	}
    	
    	while (inventImageCount < dungeon.getHeight()) {
    		addInventoryImage(null, null);
    		inventImageCount++;
    	}
    }
    
    public void addInventoryItem(Entity item) {
    	ImageView itemImage = null;
    	Text itemInfo = null;
    	
    	if (item instanceof Sword) {
    		Sword sword = (Sword) item;
    		itemImage = new ImageView(new Image("/greatsword_1_new.png"));
    		itemInfo = new Text(Integer.toString(sword.getHits()));
    	} else if (item instanceof Key) {
    		itemImage = new ImageView(new Image("/key.png"));
    	} else if (item instanceof Hammer) {
    		Hammer hammer = (Hammer) item;
    		itemImage = new ImageView(new Image("/hammer.png"));
    		itemInfo = new Text(Integer.toString(hammer.getHits()));
    	} else if (item instanceof InvincibilityPotion) {
    		InvincibilityPotion potion = (InvincibilityPotion) item;
    		itemImage = new ImageView(new Image("brilliant_blue_new.png"));
    		itemInfo = new Text(Integer.toString(potion.getMoves()));
    	} else if (item instanceof FreezePotion) {
    		FreezePotion potion = (FreezePotion) item;
    		itemImage = new ImageView(new Image("freeze_potion.png"));
    		itemInfo = new Text(Integer.toString(potion.getMoves()));
    	} else if (item instanceof GhostPotion) {
    		GhostPotion potion = (GhostPotion) item;
    		itemImage = new ImageView(new Image("ghost_potion.png"));
    		itemInfo = new Text(Integer.toString(potion.getMoves()));
    	}
    	
    	addInventoryImage(itemImage, itemInfo);
    }

    
    public void addInventoryImage(ImageView image, Text info) {
    	StackPane imageStack = new StackPane();
    	imageStack.getChildren().add(new ImageView(new Image("/empty.png")));
    	if (image != null) imageStack.getChildren().add(image);
    	if (info != null) {
    		info.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 16));
    		info.setFill(Color.BLACK);
    		info.setStrokeWidth(1);
    		info.setStroke(Color.WHITE);
    		imageStack.getChildren().add(info);
    	}
    	imageStack.setAlignment(Pos.CENTER);
    	
    	if (imageStack.getChildren().size() > 0) {    		
    		inventory.add(imageStack, 0, inventory.getChildren().size());
    	}
    }
}

