package logic.boundary;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logic.bean.BeanChangeFridge;
import logic.controller.ChangeFridgeController;
import logic.entity.Invitation;
import logic.implementationClasses.Exceptions.EmptyException;
import logic.implementationClasses.Exceptions.TooManyFridgesException;

public class ChangeFridgeUI {
	
	public ChangeFridgeUI() {
		
	}
	
	public void clickedOnDeleteAccount() {
		ChangeFridgeController changeFridgeCTRL = new ChangeFridgeController();
		changeFridgeCTRL.deleteAccount();		
	}
	
	public ObservableList<Invitation> takeInvitations() {
		try {
			ChangeFridgeController changeFridgeCTRL = new ChangeFridgeController();
			BeanChangeFridge beanChangeFridge = changeFridgeCTRL.takeInvitations();
			ArrayList<Invitation> invitationList = beanChangeFridge.getInvitationList();
			ObservableList<Invitation> data = FXCollections.observableArrayList(invitationList);
			return data;
		
		}catch( EmptyException ee ) {
			System.out.println("empty");
		}
		return null;	
	}
	
	public void clickedOnDeclineInvitation(Invitation selectedInvitations ) {
		BeanChangeFridge beanChangeFridge = new BeanChangeFridge();
		beanChangeFridge.setInvitation(selectedInvitations);
		ChangeFridgeController changeFridgeCTRL = new ChangeFridgeController();
		changeFridgeCTRL.deleteInvitation(beanChangeFridge);
	}
	
	public void clickedOnAcceptInvitation( Invitation selectedInvitations ) throws TooManyFridgesException {
		BeanChangeFridge beanChangeFridge = new BeanChangeFridge();
		beanChangeFridge.setInvitation(selectedInvitations);
		ChangeFridgeController changeFridgeCTRL = new ChangeFridgeController();
		changeFridgeCTRL.acceptInvitation(beanChangeFridge);
	}
	
	public ArrayList<String> startFridgesInterfaces(){
		try {
			ChangeFridgeController changeFridgeCTRL = new ChangeFridgeController();
			BeanChangeFridge beanChangeFridge = changeFridgeCTRL.takeMyFridges();
			return beanChangeFridge.getListOfNameFridges();
		}catch(EmptyException ee ) {
			System.out.println("empty fridge list");
		}
		return null;			
	}
	
	public void clickedOnChangeFridge( String fridgeName ) {
		BeanChangeFridge beanChangeFridge = new BeanChangeFridge();
		beanChangeFridge.setFridgeName(fridgeName);
		ChangeFridgeController changeFridgeCTRL = new ChangeFridgeController();
		changeFridgeCTRL.changeFridge(beanChangeFridge);
	}
	
	public void clickedOnChangeInMyFridge() {
		ChangeFridgeController changeFridgeCTRL = new ChangeFridgeController();
		changeFridgeCTRL.changeInMyFridge();
	}
	
}
