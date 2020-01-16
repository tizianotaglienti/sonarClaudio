package logic.application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import logic.boundary.LoginUI;

public class LoginControllerFXML implements Initializable {
	@FXML
	private TextField userInp;
	@FXML
	private PasswordField passInp;
	@FXML
	private Hyperlink hp;
	@FXML
	private Label alertLabel;
	@FXML
	private Button loginButton;
	
	private String username;
	private String password;
	private LoginUI boundaryLogin;
	
	public LoginControllerFXML() {
		boundaryLogin = new LoginUI();
		
	}
	
	@Override 
	public void initialize (URL url, ResourceBundle rb ) {
		loginButton.setDefaultButton(true);
	}
	
	public void clickedOnLogin() {
		this.username = userInp.getText();
		this.password = passInp.getText();
		if( boundaryLogin.isValid(this.username,this.password) ) {
			new Main().viewFridgeSetScene();
		}
		else {
			alertLabel.setText("Username or password not valid");
		}
	}
	
	public void clickedOnSignUp() {
		new Main().registrationSetScene();
	}
	
	public void clickedOnHomeButton() {
		new Main().loginSetScene();
		
	}
	
	
}