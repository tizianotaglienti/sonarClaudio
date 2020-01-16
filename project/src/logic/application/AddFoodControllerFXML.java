package logic.application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.controlsfx.control.textfield.TextFields;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import logic.boundary.AddFoodUI;
import logic.implementationClasses.ListAllFood;




public class AddFoodControllerFXML implements Initializable {
	Main main;
	private AddFoodUI addFoodUI;
	@FXML
    private JFXHamburger hamburger;
    
    @FXML
    private JFXDrawer leftDrawer;
    
    @FXML
    private VBox toolbar;
    
    @FXML
    private Label alertLabel;
	
	@FXML
	private Button inputFoodButton;
	
    @FXML
    private TextField foodInp;
    
    @FXML
    private TextField quantityInp;
    
    @FXML
    private DatePicker dateInp;
    
    public AddFoodControllerFXML() {
    	this.addFoodUI = new AddFoodUI();
    }

    @Override
    public void initialize (URL url, ResourceBundle rb ) {

    	try {
    	toolbar = FXMLLoader.load(getClass().getResource("toolbar.fxml"));
    	leftDrawer.setSidePane(toolbar);
    	}catch(IOException ex) {
    		Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    	}
    	HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
    	transition.setRate(-1);
    	hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
    		transition.setRate(transition.getRate() * -1 ); 
    		transition.play();
    		
    		if( leftDrawer.isOpened() ) {
    			leftDrawer.close();
    		}
    		else {
    			leftDrawer.open();
    		}
    	});
    	
    	TextFields.bindAutoCompletion( foodInp, ListAllFood.getListAllFood() );
    }
    
    public void clickedOnInputFood() {
    	try {
    		alertLabel.setText("");
    		String name = foodInp.getText();
	    	int quantity = Integer.parseInt(quantityInp.getText());
	    	System.out.println(quantity);
	    	LocalDate expirationDate = dateInp.getValue();
	    	if( !addFoodUI.validSemanticName( name )  ) {
	    		//error message
	    		alertLabel.setText("Name not valid");
	    		return;		
	    	}
	    	if( !addFoodUI.validSyntaxQuantity( quantity )  ) {
	    		//error message
	    		alertLabel.setText("Quantity not valid");
	    		return;		
	    	}
	    	
	    	this.addFoodUI.clickOnInsertFood( name, quantity, expirationDate);
	    	foodInp.clear();
	    	quantityInp.clear();
	    	dateInp.getEditor().clear();
    	}catch(Exception e) {
    		//error on quantity input
    		alertLabel.setText("Quantity not valid");
    	}
    }
    
}