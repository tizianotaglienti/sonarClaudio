package logic.application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AlertBoxControllerFXML  {
	@FXML
	private Button noButton;
	
	@FXML
	private Button yesButton;
	
	@FXML
	private TextField messageInp;
	
	public void clickedOnYes() {
		String message = messageInp.getText();
		ShareFridgeControllerFXML.requestConfirmed(message);
		ShareFridgeControllerFXML.getAlertStage().close();
	}
	
	public void clickedOnNo() {
		//exit
		ShareFridgeControllerFXML.getAlertStage().close();
	}
	

}
