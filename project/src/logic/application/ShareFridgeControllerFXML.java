package logic.application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logic.boundary.ShareFridgeUI;





public class ShareFridgeControllerFXML implements Initializable {
	//Main main;
	private static ShareFridgeUI shareFridgeUI;
	private static String username;
	private static Stage alertWindow; 
	@FXML
    private JFXHamburger hamburger;
    
    @FXML
    private JFXDrawer leftDrawer;
    
    @FXML
    private VBox toolbar;
    
    @FXML
	private Label alertLabel;
	
	@FXML
	private Button usernameButton;
	
	@FXML
	private Button emailButton;
	
    @FXML
    private TextField usernameInp;
    
    @FXML
    private TextField emailInp;
    
    
    
    public ShareFridgeControllerFXML() {
    	shareFridgeUI = new ShareFridgeUI();
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
    
    }
    
    public void clickedOnInviteWithUsername() {
    	alertLabel.setText("");
    	username =  usernameInp.getText();
    	if( !shareFridgeUI.isValidUsername( username ) ) {
    		//error message
    		alertLabel.setText("Username not valid");
    		return;
    	}
    	try {
    		alertWindow = new Stage();
    		alertWindow.initModality( Modality.APPLICATION_MODAL);
    		alertWindow.setTitle("Confirm invitation");
			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("alertBox.fxml"));
			Scene scene = new Scene(root,370,270);
			scene.getStylesheets().add(getClass().getResource("bootstrap3.css").toExternalForm());
			alertWindow.setScene(scene);
			alertWindow.showAndWait();
		} catch(Exception e) {
			e.printStackTrace();
		}   	
    	usernameInp.clear();
    }
    
    
    public void clickedOnInviteWithEmail() {
    	/*alertLabel.setText("");
    	String email =  emailInp.getText();
    	if( !shareFridgeUI.isValidEmail( email ) ) {
    		//error message
    		alertLabel.setText("Email not valid");
    		return;
    	}
    	//shareFridgeUI.clickedOnInviteWithEmail(email);
    	emailInp.clear(); */
    }
   
    
    public void clickedOnInviteWithFB() {
    	
    }
    
    public static void requestConfirmed( String message ) {
    	shareFridgeUI.clickedOnInviteWithUsername(username , message);
    	System.out.println(message);
    }
    
    public static Stage getAlertStage() {
    	return alertWindow;
    }
    
    
    
    
    
    
    
}