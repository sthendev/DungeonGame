package unsw.dungeon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class TutorialController {

    @FXML
    private ImageView image;

    @FXML
    private Button menuButton;

    @FXML
    private Button nextButton;

    @FXML
    private Button backButton;
    
    private Stage primaryStage;
    
    private int currentImageIndex;
    
    private List<Image> images;
    
    public TutorialController(Stage primaryStage) {
    	this.primaryStage = primaryStage;
    	this.currentImageIndex = 0;
    	this.images = new ArrayList<>();
    }
    
    /**
     * initialise tutorial screens
     */
    @FXML
    void initialize() {
    	images.add(new Image("gameplay.png"));
    	images.add(new Image("collectables.png"));
    	images.add(new Image("others.png"));
    	images.add(new Image("goals.png"));
    	
    	image.setImage(images.get(currentImageIndex));
    	disableButtons();
    }
    
    /**
     * go back a screen
     * @param event
     */
    @FXML
    void goBack(MouseEvent event) {
    	if (currentImageIndex != 0) {
    		currentImageIndex--;
    		image.setImage(images.get(currentImageIndex));
    		disableButtons();
    	}
    }

    /**
     * go back to main menu
     * @param event
     * @throws IOException
     */
    @FXML
    void goMenu(MouseEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuView.fxml"));
        loader.setController(new MenuController(primaryStage));
        Parent root = loader.load(); 
        Scene scene = new Scene(root);
        root.requestFocus();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * go to next screen
     * @param event
     */
    @FXML
    void goNext(MouseEvent event) {
    	if (currentImageIndex != images.size() - 1) {
    		currentImageIndex++;
    		image.setImage(images.get(currentImageIndex));
    		disableButtons();
    	}
    }
    
    /**
     * disable back or next buttons appropriately
     */
    public void disableButtons() {
    	backButton.setDisable(false);
    	nextButton.setDisable(false);
    	if (currentImageIndex == 0) {
    		backButton.setDisable(true);
    	} else if (currentImageIndex == images.size() - 1) {
    		nextButton.setDisable(true);
    	}
    }

}
