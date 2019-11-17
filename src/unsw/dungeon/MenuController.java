package unsw.dungeon;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MenuController {
	
	@FXML
	private Pane pane;

    @FXML
    private ImageView image;
    
    @FXML
    private ChoiceBox<String> levelSelect;

    @FXML
    private Button startButton;
    
    @FXML
    private Button tutButton;
    
    @FXML
    private Label label;
    
    private Stage primaryStage;
    
    
    public MenuController(Stage primaryStage) {
    	this.primaryStage = primaryStage;
    }
    
    /**
     * iniialize main menu UI
     */
    @FXML
    void initialize() {
    	File dungeonFolder = new File("dungeons");
    	File [] dungeonFiles = dungeonFolder.listFiles();
    	for (File file : dungeonFiles) {
    		if (file.getName().contains(".json")) levelSelect.getItems().add(trimExtension(file.getName()));
    	}
    	SoundPlayer sd = new SoundPlayer();
    	sd.playSound("music.wav");
    	label.setTextFill(Color.WHITESMOKE);
    	startButton.setDisable(true);
    	levelSelect.getSelectionModel()
        	.selectedItemProperty()
        	.addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
        		if (newValue != null) {
        			startButton.setDisable(false);
        		}
        	});
    	
    	try {
			image.setImage(new Image(this.getClass().getResource("bg.png").toURI().toString()));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * 
     * @param filename
     * @return filename with extension trimmed
     */
    public String trimExtension(String filename) {
    	return filename.substring(0, filename.lastIndexOf('.'));
    }

    /**
     * start selected dungeon level
     * @param event
     * @throws IOException
     */
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
    /**
     * open tutorial screen
     * @param event
     * @throws IOException 
     */
    @FXML
    void onTutClick(MouseEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("TutorialView.fxml"));
        loader.setController(new TutorialController(primaryStage));
        Parent root = loader.load(); 
        Scene scene = new Scene(root);
        root.requestFocus();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}

