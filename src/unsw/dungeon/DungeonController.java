package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {

    @FXML
    private GridPane squares;

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

        for (ImageView entity : initialEntities)
            squares.getChildren().add(entity);

    }

    @FXML
    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
        case UP:
        	if (dungeon.canMove(player, player.getX(), player.getY() + 1)) {
        		player.move(0, 1);
        	}
            break;
        case DOWN:
        	if (dungeon.canMove(player, player.getX(), player.getY() - 1)) {
                player.move(0, -1);
        	}
            break;
        case LEFT:
        	if (dungeon.canMove(player, player.getX() - 1, player.getY())) {
                player.moveLeft(-1, 0);
        	}
            break;
        case RIGHT:
        	if (dungeon.canMove(player, player.getX() + 1, player.getY())) {
                player.moveRight(1, 0);
        	}
            break;
        default:
            break;
        }
    }

}

