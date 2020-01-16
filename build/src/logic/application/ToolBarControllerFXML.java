package logic.application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;


public class ToolBarControllerFXML implements Initializable {
	private Main main;
	
	@FXML
	private Button yourFridgeButton;
	
    @FXML
    private Button addFoodButton;
    
    @FXML
    private Button chooseRecipeButton;
    
    @FXML
    private Button shareFridgeButton;
    
    @FXML
    private Button familyMembersButton;
    
    @FXML
    private Button logoutButton;
    
    @FXML
    private Button changeFridgeButton;
    
    public ToolBarControllerFXML() {
    	this.main = new Main();
    }

    @Override
    public void initialize (URL url, ResourceBundle rb ) {
    	
    	
    }
    
    public void clickedOnYourFridge() {
    	main.viewFridgeSetScene();
    }
    
    public void clickedOnLogout() {
    	main.loginSetScene();
    }
    
    public void clickedOnAddFood() {
    	main.addFoodSetScene();
    }
    public void clickedOnChooseRecipes(){
    	main.chooseRecipesSetScene();
    }
    public void clickedOnShareFridge(){
    	main.shareFridgeSetScene();
    }
    
    public void clickedOnChangeFridge() {
    	main.changeFridgeSetScene();
    }
    
}