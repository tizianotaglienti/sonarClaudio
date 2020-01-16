package logic.application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import logic.boundary.RegistrationUI;

public class RegistrationControllerFXML implements Initializable {
	@FXML
	private TextField userInp;
	@FXML
	private PasswordField passInp;
	@FXML
	private TextField emailInp;
	@FXML
	private HBox errorImageBox;
	@FXML
	private Label errorLabel;
	@FXML
	private Button registrationButton;

	private RegistrationUI boundaryRegistration;
	
	public RegistrationControllerFXML() {		
		this.boundaryRegistration = new RegistrationUI();
	}
	
	@Override 
	public void initialize (URL url, ResourceBundle rb ) {
		registrationButton.setDefaultButton(true);
	}
	
	public void clickedOnSignUp() {
		String username = userInp.getText();
		String email = emailInp.getText();
		String password = passInp.getText();
		if( !boundaryRegistration.validSyntaxUsername(username) ) {
			//error message
			errorLabel.setText("Username not valid (from 4 to 30 chars)");
			return;
		}		
		if( !boundaryRegistration.validSyntaxEmail(email) ) {
			//error message
			errorLabel.setText("Email not valid");
			return;
		}
		if( !boundaryRegistration.validSyntaxPassword(password) ) {
			//error message
			errorLabel.setText("Password not valid, (from 6 to 30 chars)");
			return;
		}
		if( boundaryRegistration.notExist(username, email , password) == true ) {
			boundaryRegistration.clickedOnRegistration( username, email , password);
			new Main().viewFridgeSetScene();
		}
		else {
			errorLabel.setText("Username or email already exist");
		}
	}
	
	public void clickedOnHomeButton() {
		new Main().loginSetScene();
		
	}
	
	
}