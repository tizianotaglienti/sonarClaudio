package logic.controller;

import java.util.ArrayList;

import logic.bean.BeanChangeFridge;
import logic.dao.DaoInvitation;
import logic.dao.DaoUser;
import logic.entity.Fridge;
import logic.entity.Invitation;
import logic.entity.User;
import logic.implementationClasses.SingletonInstances;
import logic.implementationClasses.Exceptions.EmptyException;
import logic.implementationClasses.Exceptions.TooManyFridgesException;

public class ChangeFridgeController {
	
	
	public ChangeFridgeController() {
		
	}
	
	public void deleteAccount() {
		String username = SingletonInstances.getCurrentUser().getUsername();
		User user = new User();
		user.setUsername(username);
		DaoUser daoUser = new DaoUser();
		daoUser.deleteUser( user );
	}
	
	public BeanChangeFridge takeInvitations() throws EmptyException {
		String username = SingletonInstances.getCurrentUser().getUsername();
		DaoInvitation daoInvitation = new DaoInvitation();
		ArrayList<Invitation> invitationList = daoInvitation.takeInvitationsFromDB( username );
		BeanChangeFridge beanChangeFridge = new BeanChangeFridge();
		beanChangeFridge.setInvitationList(invitationList);
		return beanChangeFridge;
	}
	
	public void deleteInvitation( BeanChangeFridge beanChangeFridge ) {
		Invitation declineInvitation = beanChangeFridge.getInvitation();
		DaoInvitation daoInvitation = new DaoInvitation();
		daoInvitation.deleteInvitationFromDB( declineInvitation );
	}
	
	public void acceptInvitation( BeanChangeFridge beanChangeFridge ) throws TooManyFridgesException {
		DaoUser daoUser = new DaoUser();
		User invitedUser = new User();
		invitedUser.setUsername(SingletonInstances.getCurrentUser().getUsername());
		int c = daoUser.countMembership(invitedUser);
		System.out.println(c);
		if( c < 4 ) {			
			Invitation invitation = beanChangeFridge.getInvitation();
			// loop: per ogni invito divento user del frigo dell'utente che mi ha invitato ----> aggiungo il frigo in change fridge dell'utente invitato
			
			String invitingUser = invitation.getInvitingUser();
			Fridge fridge = new Fridge();
			User user = new User();
			user.setUsername(invitingUser);
			fridge = daoUser.getFridgeOfUser(user);
			DaoInvitation daoInvitation = new DaoInvitation();
			daoInvitation.startMembership( invitation , fridge );
			daoInvitation.deleteInvitationFromDB( invitation );
		}
		else {
			TooManyFridgesException te = new TooManyFridgesException("too many fridges");
			throw te;
		}
	}
	
	public BeanChangeFridge takeMyFridges() throws EmptyException {
		User user = new User();
		user.setUsername(SingletonInstances.getCurrentUser().getUsername() );
		DaoUser daoUser = new DaoUser();
		ArrayList<Fridge> listOfFridges = daoUser.takeFridgesFromDB(user);
		ArrayList<String> listOfNameFridges = new ArrayList<String>( listOfFridges.size() );
		for( int i=0; i< listOfFridges.size() ; i++ ) {	
			listOfNameFridges.add( i, listOfFridges.get(i).getName() );			
		}
		System.out.println(listOfNameFridges);
		BeanChangeFridge beanChangeFridge = new BeanChangeFridge();
		beanChangeFridge.setListOfNameFridges(listOfNameFridges);
		return beanChangeFridge;
		
	}
	
	public void changeFridge( BeanChangeFridge beanChangeFridge ) {
		String fridgeName = beanChangeFridge.getFridgeName();
		SingletonInstances.takeFridgeByName(fridgeName);
		SingletonInstances.getSingletonInstance().becomeUser();
	
	}
	
	public void changeInMyFridge() {
		if( !SingletonInstances.getCurrentUser().isAdmin() ) {
			SingletonInstances.setCurrentFridge(SingletonInstances.getMyFridge());
			SingletonInstances.getSingletonInstance().becomeAdmin();
		}
	}
	
}


