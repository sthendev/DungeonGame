package unsw.dungeon;

import java.io.File;
import java.io.IOException;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MenuController {
	
	@FXML
	private Pane pane;

    @FXML
    private ChoiceBox<String> levelSelect;

    @FXML
    private Button startButton;
    
    @FXML
    private Button tutButton;
    
    @FXML
    private HBox pictures;
    
    private Stage primaryStage;
    
    
    public MenuController(Stage primaryStage) {
    	this.primaryStage = primaryStage;
    }
    
    @FXML
    void initialize() {
    	File dungeonFolder = new File("dungeons");
    	File [] dungeonFiles = dungeonFolder.listFiles();
    	for (File file : dungeonFiles) {
    		if (file.getName().contains(".json")) levelSelect.getItems().add(trimExtension(file.getName()));
    		System.out.println(file.getName());
    	}
    	startButton.setDisable(true);
    	levelSelect.getSelectionModel()
        	.selectedItemProperty()
        	.addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
        		if (newValue != null) {
        			startButton.setDisable(false);
        		}
        	});
    	pane.setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));
    	
    	pictures.setSpacing(65);
    	pictures.getChildren().add(new ImageView(new Image("human_new.png")));
    	pictures.getChildren().add(new ImageView(new Image("gold_pile.png")));
    	pictures.getChildren().add(new ImageView(new Image("ghost_potion.png")));
    	pictures.getChildren().add(new ImageView(new Image("greatsword_1_new.png")));
    	pictures.getChildren().add(new ImageView(new Image("gnome.png")));
    	
    }
    
    public String trimExtension(String filename) {
    	return filename.substring(0, filename.lastIndexOf('.'));
    }

    @FXML
    void onStartClick(MouseEvent event) throws IOException {
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(levelSelect.getSelectionModel().selectedItemProperty().get() + ".json");
        DungeonController controller = dungeonLoader.loadController(primaryStage);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);
        Parent root = loader.load(); 
        Scene scene = new Scene(root);
        root.requestFocus();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}

