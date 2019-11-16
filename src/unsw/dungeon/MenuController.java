package unsw.dungeon;

import java.io.File;
import java.io.IOException;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MenuController {

    @FXML
    private ChoiceBox<String> levelSelect;

    @FXML
    private Button startButton;
    
    private Stage primaryStage;
    
    
    public MenuController(Stage primaryStage) {
    	this.primaryStage = primaryStage;
    }
    
    @FXML
    void initialize() {
    	File dungeonFolder = new File("dungeons");
    	File [] dungeonFiles = dungeonFolder.listFiles();
    	for (File file : dungeonFiles) {
    		levelSelect.getItems().add(trimExtension(file.getName()));
    	}
    	startButton.setDisable(true);
    	levelSelect.getSelectionModel()
        	.selectedItemProperty()
        	.addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
        		if (newValue != null) {
        			startButton.setDisable(false);
        		}
        	});
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

