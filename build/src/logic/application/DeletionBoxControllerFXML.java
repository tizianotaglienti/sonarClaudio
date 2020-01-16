package logic.application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DeletionBoxControllerFXML  {
	Main main;
	@FXML
	private Button noButton;
	
	@FXML
	private Button yesButton;
	
	public void clickedOnYes() {
		ChangeFridgeControllerFXML.deletionConfirmed();
		ChangeFridgeControllerFXML.getDeletionStage().close();
		main = new Main();
		main.loginSetScene();
	}
	
	public void clickedOnNo() {
		//exit
		ChangeFridgeControllerFXML.getDeletionStage().close();
	}
	

}
