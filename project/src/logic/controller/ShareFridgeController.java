package logic.controller;

import logic.bean.BeanShareFridge;
import logic.dao.DaoInvitation;
import logic.entity.Invitation;
import logic.implementationClasses.SingletonInstances;
import logic.implementationClasses.GoF.FacadeCheckUsername;


public class ShareFridgeController {
	
	public boolean isValidUsername( String username ) {
		if( username == SingletonInstances.getCurrentUser().getUsername() ) return false;
		FacadeCheckUsername check = new FacadeCheckUsername();
		if( check.usernameExist(username) ) {
			return true;
		}
		return false;
	}
	
	/*public boolean isValidEmail( String email ) {
		DaoUser daoUser = new DaoUser();
		User user = new User();
		user.setEmailAddress(email);
		if( daoUser.checkValidEmail(user) ) return true;
		return false;
		
	}*/
	
	public void inviteWithUsername( BeanShareFridge beanShareFridge ) {
		beanShareFridge.setInvitingUsername( SingletonInstances.getCurrentUser().getUsername() );
		
		Invitation invitation = new Invitation();		
		invitation.setInvitedUser( beanShareFridge.getInvitedUsername() );
		invitation.setInvitingUser(beanShareFridge.getInvitingUsername());
		invitation.setMessage(beanShareFridge.getMessage());
		
		DaoInvitation daoInvitation = new DaoInvitation();
		daoInvitation.sendInvitationDB( invitation );
	}
		
	public void inviteWithEmail( BeanShareFridge beanShareFridge ) {
		beanShareFridge.setInvitingUsername( SingletonInstances.getCurrentUser().getUsername() );
	}
	
}
