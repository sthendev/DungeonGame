package unsw.dungeon;

import java.util.ArrayList;

import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Node;

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController implements Observer{
	
	@FXML
	private StackPane stackPane;
	
	@FXML
	private Pane pane;

    @FXML
    private GridPane squares;
    
    @FXML
    private GridPane inventory;
    
    @FXML
    private VBox goals;
    
    private String jsonFile;
    
    private Stage primaryStage;

    private List<ImageView> initialEntities;

    private Player player;

    private Dungeon dungeon;
    
    private VBox pauseMenu;
    
    private Text goalInfo;
    
    private Button restartButton;
    
    private Button homeButton;
    
    private Button returnButton;
    
    private Node goalsNode;
    
    private boolean isPaused;

    public DungeonController(Stage primaryStage, String jsonFile, Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
        this.primaryStage = primaryStage;
        this.jsonFile = jsonFile;
    }

    @FXML
    public void initialize() {
    	dungeon.setController(this);
    	
    	goals.setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));
        goals.setMinHeight(32 * dungeon.getHeight());
        goals.setPadding(new Insets(10, 10, 10, 10));
        Text goalHeader = new Text("Goals:");
        goalHeader.setFont(Font.font("verdana", FontWeight.BOLD, 20));
        goals.getChildren().add(goalHeader);
        goalHeader.toFront();
        this.goalsNode = parseGoalToNode(dungeon.getGoal());
        goals.getChildren().add(goalsNode);
        
        squares.setLayoutX(300);
        Image ground = new Image("/dirt_0_new.png");

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
            	ImageView groundImage = new ImageView(ground);
            	groundImage.setViewOrder(1);
                squares.add(groundImage, x, y);
            }
        }

        for (ImageView entity : initialEntities) {
        	squares.getChildren().add(entity);
        }
        
        inventory.setLayoutX(300 + 32 * dungeon.getWidth());
        
        for (int y = 0; y < dungeon.getHeight(); y++) {
        	inventory.add(new ImageView(new Image("/empty.png")), 0, y);
        }
        dungeon.getPlayer().getInventory().addObserver(this);
        initialisePauseMenu();
    }

    @FXML
    public void handleKeyPress(KeyEvent event) {
    	if (dungeon.gameOver()) {
    		openPauseMenu(true);
    	}
    	if (isPaused) return;
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
        case ESCAPE:
        	openPauseMenu(false);
        default:
            break;
        }
        updateGoals();
    }
    
    public Node parseGoalToNode(Goal goal) {
    	Text goalMessage = new Text(goal.message());
    	goalMessage.setFont(Font.font("verdana", 14));
    	if (goal instanceof CompositeGoal) {
    		CompositeGoal composite = (CompositeGoal) goal;
    		VBox vbox = new VBox();
    		vbox.getChildren().add(goalMessage);
    		VBox subgoals = new VBox();
    		subgoals.setPadding(new Insets(0,0,0,10));
    		for (Goal g : composite.getGoals()) {
    			subgoals.getChildren().add(parseGoalToNode(g));
    		}
    		vbox.getChildren().add(subgoals);
    		return vbox;
    	}
    	return goalMessage;
    }
    
    public void initialisePauseMenu() {
    	this.pauseMenu = new VBox();
    	pauseMenu.setSpacing(20);
    	pauseMenu.setPadding(new Insets(20, 20, 20, 20));
    	pauseMenu.setMaxWidth(400);
    	pauseMenu.setMaxHeight(200);
    	pauseMenu.setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));
    	pauseMenu.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
    	
    	
    	this.goalInfo = new Text("Paused");
    	goalInfo.setFont(Font.font("verdana", FontWeight.BOLD, 16));
    	
    	this.restartButton = new Button("Restart Level");
    	restartButton.setMaxWidth(Double.MAX_VALUE);
    	restartButton.setOnMouseClicked((event) -> restartLevel());
    	
    	this.homeButton = new Button("Back to Main Menu");
    	homeButton.setMaxWidth(Double.MAX_VALUE);
    	homeButton.setOnMouseClicked((event) -> backToMenu());
    	
    	this.returnButton = new Button("Continue Game");
    	returnButton.setMaxWidth(Double.MAX_VALUE);
    	returnButton.setOnMouseClicked((event) -> closePauseMenu());
    	
    	pauseMenu.getChildren().addAll(goalInfo, restartButton, homeButton, returnButton);
    	
    	stackPane.getChildren().add(pauseMenu);
    	pauseMenu.setVisible(false);
    }
    
    @Override
    public void update(Subject obj) {
    	if (obj instanceof Inventory) {
    		
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
    
    public void openPauseMenu(boolean gameOver) {
    	this.isPaused = true;
    	if (gameOver) {
    		boolean goalSatisfied = dungeon.getGoal().satisfied();
    		goalInfo.setText( goalSatisfied ? "Congratulations! You win!" : "Bad luck :( Try Again?");
    		pauseMenu.getChildren().remove(returnButton);
    		SoundPlayer sd = new SoundPlayer();
        	if (goalSatisfied) {
        		sd.playSound("success.wav");
        	} else {
        		sd.playSound("fail.wav");
        	}
    	}
    	pauseMenu.setVisible(true); 	
    }
    
    public void closePauseMenu() {
    	this.isPaused = false;
    	pauseMenu.setVisible(false);
    	stackPane.requestFocus();
    }
    
    public void restartLevel() {
    	try {
    		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(jsonFile);
            DungeonController controller = dungeonLoader.loadController(primaryStage);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
            loader.setController(controller);
            Parent root = loader.load(); 
            Scene scene = new Scene(root);
            root.requestFocus();
            primaryStage.setScene(scene);
            primaryStage.show();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public void backToMenu() {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuView.fxml"));
            loader.setController(new MenuController(primaryStage));
            Parent root = loader.load(); 
            Scene scene = new Scene(root);
            root.requestFocus();
            primaryStage.setScene(scene);
            primaryStage.show();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public void updateGoals() {
    	goals.getChildren().remove(goalsNode);
    	this.goalsNode = parseGoalToNode(dungeon.getGoal());
    	goals.getChildren().add(goalsNode);
    }
}

